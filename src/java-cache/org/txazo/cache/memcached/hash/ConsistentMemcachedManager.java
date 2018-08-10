package org.txazo.cache.memcached.hash;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ConsistentMemcachedManager implements MemcachedManager {

	/** 默认虚拟节点数 */
	private static int defaultVirtualNode = 10;
	/** Memcached服务器集群 */
	private static List<String> serverList = new ArrayList<String>();

	/** 虚拟节点数 */
	private int virtualNode;
	/** 一致性Hash */
	private ConsistentHash consistentHash;
	/** 一致性Hash圆环 */
	private SortedMap<Integer, Node> circleMap = new ConcurrentSkipListMap<Integer, Node>();
	/** 服务器节点集 */
	private Map<String, ConsistentMemcachedClient> serverMap = new HashMap<String, ConsistentMemcachedClient>();

	static {
		serverList.add("112.124.6.220:9998");
		serverList.add("112.124.6.220:9999");
	}

	public ConsistentMemcachedManager() {
		this(defaultVirtualNode);
	}

	public ConsistentMemcachedManager(int virtualNode) {
		if (virtualNode < 1) {
			throw new IllegalArgumentException("Illegal virtualNode: " + virtualNode);
		}

		this.virtualNode = virtualNode;
		consistentHash = new ConsistentHash();

		initServer();
	}

	/** 初始化Memcached服务器连接 */
	private void initServer() {
		String[] str = null;
		for (String server : serverList) {
			str = server.split(":");
			addServer(str[0], NumberUtils.toInt(str[1], 0));
		}
	}

	private Node getNode(String key) {
		int hash = consistentHash.hash(key);
		if (!circleMap.containsKey(hash)) {
			SortedMap<Integer, Node> tailMap = circleMap.tailMap(hash);
			hash = tailMap.isEmpty() ? circleMap.isEmpty() ? 0 : circleMap.firstKey() : tailMap.firstKey();
		}
		return circleMap.get(hash);
	}

	@Override
	public Object get(String key) {
		Node node = getNode(key);
		return node != null ? node.get(key) : null;
	}

	@Override
	public Object asyncGet(String key) {
		Node node = getNode(key);
		return node != null ? node.asyncGet(key) : null;
	}

	@Override
	public void set(String key, Object value, int expries) {
		Node node = getNode(key);
		if (node != null) {
			node.set(key, value, expries);
		}
	}

	public static boolean checkHost(String ip, int port) {
		if (StringUtils.isEmpty(ip) || port < 1024 || port > 65535) {
			return false;
		}

		String[] strs = ip.split("\\.");
		if (strs == null || strs.length != 4) {
			return false;
		}

		int p = 0;
		for (int i = 0; i < strs.length; i++) {
			if ((p = NumberUtils.toInt(strs[i], 0)) < 1 || p > 255) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String addServer(String ip, int port) {
		if (!checkHost(ip, port)) {
			return "server format error";
		}

		String serverName = ip + ":" + port;
		if (serverMap.containsKey(serverName)) {
			return "server exists";
		}

		ConsistentMemcachedClient consistentClient = new ConsistentMemcachedClient(ip, port);
		serverMap.put(serverName, consistentClient);

		/** 初始化虚拟节点 */
		int nodeHash = 0;
		String nodeName = null;
		for (int i = 0; i < virtualNode; i++) {
			nodeName = serverName + "#" + i;
			nodeHash = consistentHash.hash(nodeName);
			consistentClient.addNode(new Node(nodeName, nodeHash, consistentClient));
		}

		System.out.println("server[" + serverName + "] add success");

		return "server add success";
	}

	@Override
	public String removeServer(String ip, int port) {
		if (!checkHost(ip, port)) {
			return "host error";
		}

		String serverName = ip + ":" + port;
		if (!serverMap.containsKey(serverName)) {
			return "host not exists";
		}

		System.out.println("server[" + serverName + "] remove success");

		return "server remove success";
	}

	/** ConsistentMemcachedClient */
	private class ConsistentMemcachedClient implements MemcachedManager {

		private transient boolean calcled;
		private String serverName;
		private final ReentrantLock lock;
		private MemcachedClient client;
		private InetSocketAddress socketAddress;
		private List<Node> nodeList = new ArrayList<Node>();

		public ConsistentMemcachedClient(String ip, int port) {
			calcled = true;
			serverName = ip + ":" + port;
			socketAddress = new InetSocketAddress(ip, port);
			try {
				client = new MemcachedClient(socketAddress);
				client.addObserver(new MemcachedObserver(this));
			} catch (IOException e) {
				e.printStackTrace();
			}
			lock = new ReentrantLock(false);
		}

		@Override
		public Object get(String key) {
			try {
				return calcled ? null : client.get(key);
			} catch (CancellationException e) {
				return null;
			}
		}

		@Override
		public Object asyncGet(String key) {
			if (calcled) {
				return null;
			}

			Object value = null;
			Future<Object> future = client.asyncGet(key);
			try {
				value = future.get(5, TimeUnit.SECONDS);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				future.cancel(false);
			}
			return value;
		}

		@Override
		public void set(String key, Object value, int expries) {
			try {
				if (!calcled) {
					client.set(key, expries, value);
				}
			} catch (CancellationException e) {
			}
		}

		public void addNode(Node node) {
			nodeList.add(node);
		}

		public void addNodesToCircle() {
			Node node = null;
			lock.lock();
			try {
				for (int i = 0, size = nodeList.size(); i < size; i++) {
					node = nodeList.get(i);
					System.out.println(node + " add success");
					ConsistentMemcachedManager.this.circleMap.put(node.getNodeHash(), node);
				}
			} finally {
				lock.unlock();
			}

			calcled = false;
		}

		public void removeNodesFromCircle() {
			calcled = true;
			Node node = null;
			lock.lock();
			try {
				for (int i = 0, size = nodeList.size(); i < size; i++) {
					node = nodeList.get(i);
					System.out.println(node + " remove success");
					ConsistentMemcachedManager.this.circleMap.remove(node.getNodeHash());
				}
			} finally {
				lock.unlock();
			}
		}

		@Override
		public int hashCode() {
			return serverName.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ConsistentMemcachedClient other = (ConsistentMemcachedClient) obj;
			return serverName != null ? serverName.equals(other.serverName) : other.serverName == null ? true : false;
		}

		@Override
		public String toString() {
			return "ConsistentMemcached " + socketAddress.getHostName() + ":" + socketAddress.getPort();
		}

		@Override
		public String addServer(String ip, int port) {
			return null;
		}

		@Override
		public String removeServer(String ip, int port) {
			return null;
		}

	}

	/** ConsistentHash */
	private class ConsistentHash {

		public int hash(String key) {
			int hash = (int) 2166136261L;
			for (int i = 0; i < key.length(); i++) {
				hash *= 16777619;
				hash ^= key.charAt(i);
			}
			return hash;
		}

	}

	/** Node */
	private class Node implements MemcachedManager {

		private String nodeName;
		private int nodeHash;
		private ConsistentMemcachedClient consistentClient;

		public Node(String nodeName, int nodeHash, ConsistentMemcachedClient consistentClient) {
			this.nodeName = nodeName;
			this.nodeHash = nodeHash;
			this.consistentClient = consistentClient;
		}

		@Override
		public Object get(String key) {
			System.out.println(nodeName + " get");
			return consistentClient != null ? consistentClient.get(key) : null;
		}

		@Override
		public Object asyncGet(String key) {
			System.out.println(nodeName + " asyncGet");
			return consistentClient != null ? consistentClient.asyncGet(key) : null;
		}

		@Override
		public void set(String key, Object value, int expries) {
			System.out.println(nodeName + "set");
			if (consistentClient != null) {
				consistentClient.set(key, value, expries);
			}
		}

		public int getNodeHash() {
			return nodeHash;
		}

		@Override
		public String addServer(String ip, int port) {
			return null;
		}

		@Override
		public String removeServer(String ip, int port) {
			return null;
		}

		@Override
		public String toString() {
			return "Node [" + nodeName + "]";
		}

	}

	/** MemcachedObserver */
	private class MemcachedObserver implements ConnectionObserver {

		private ConsistentMemcachedClient consistentClient;

		public MemcachedObserver(ConsistentMemcachedClient consistentClient) {
			this.consistentClient = consistentClient;
		}

		/** Memcached建立连接 */
		@Override
		public void connectionEstablished(SocketAddress address, int repeatConnectCount) {
			System.out.println(consistentClient + " ConnectionEstablished");
			consistentClient.addNodesToCircle();
		}

		/** Memcached断开连接 */
		@Override
		public void connectionLost(SocketAddress address) {
			System.out.println(consistentClient + " ConnectionLost");
			consistentClient.removeNodesFromCircle();
		}

	}

}
