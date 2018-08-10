package org.txazo.cache.memcached.spy.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;

/**
 * SpyMemcached
 * 
 * @author txazo
 * 
 */
public class SpyMemcachedManager {

	private static SpyMemcachedManager instance;
	private static ArrayList<String> addressList = new ArrayList<>();

	private ReentrantLock lock;
	private MemcachedClient memcachedClient;

	static {
		addressList.add("112.124.6.220:9998");
		addressList.add("112.124.6.220:9999");
	}

	private SpyMemcachedManager() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init() throws IOException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, size = addressList.size(); i < size; i++) {
			if (i < size - 1) {
				sb.append(addressList.get(i)).append(" ");
			} else {
				sb.append(addressList.get(i));
			}
		}

		lock = new ReentrantLock();
		memcachedClient = new MemcachedClient(AddrUtil.getAddresses(sb.toString()));
	}

	public static SpyMemcachedManager getInstance() {
		if (instance == null) {
			synchronized (SpyMemcachedManager.class) {
				if (instance == null) {
					instance = new SpyMemcachedManager();
				}
			}
		}
		return instance;
	}

	public void shutdown() {
		if (memcachedClient != null) {
			lock.lock();
			try {
				memcachedClient.shutdown();
			} finally {
				lock.unlock();
			}
		}
	}

	public void addObserver(ConnectionObserver observer) {
		memcachedClient.addObserver(observer);
	}

	public void removeObserver(ConnectionObserver observer) {
		memcachedClient.removeObserver(observer);
	}

	public Object get(String key) {
		return memcachedClient.get(key);
	}

	public Object asyncGet(String key) {
		Object value = null;
		Future<Object> future = memcachedClient.asyncGet(key);
		try {
			value = future.get(SpyMemcachedConstant.DEFAULT_TIMEOUT, SpyMemcachedConstant.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			future.cancel(false);
		}
		return value;
	}

	public void set(String key, Object object, int expries) {
		memcachedClient.set(key, expries, object);
	}

	public void add(String key, Object object, int expries) {
		memcachedClient.add(key, expries, object);
	}

	public void replace(String key, Object object, int expries) {
		memcachedClient.replace(key, expries, object);
	}

	public void delete(String key) {
		memcachedClient.delete(key);
	}

	public Map<String, Object> getMulti(String[] keys) {
		return memcachedClient.getBulk(keys);
	}

	public Map<String, Object> getMulti(Collection<String> keys) {
		return memcachedClient.getBulk(keys);
	}

	public Map<String, Object> asyncGetMulti(String[] keys) {
		Map<String, Object> map = null;
		Future<Map<String, Object>> future = memcachedClient.asyncGetBulk(keys);
		try {
			map = future.get(SpyMemcachedConstant.DEFAULT_TIMEOUT, SpyMemcachedConstant.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			future.cancel(false);
		}
		return map;
	}

	public Map<String, Object> asyncGetMulti(Collection<String> keys) {
		Map<String, Object> map = null;
		Future<Map<String, Object>> future = memcachedClient.asyncGetBulk(keys);
		try {
			map = future.get(SpyMemcachedConstant.DEFAULT_TIMEOUT, SpyMemcachedConstant.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			future.cancel(false);
		}
		return map;
	}

	public long incr(String key, long by) {
		return memcachedClient.incr(key, by);
	}

	public long incr(String key, long by, long def) {
		return memcachedClient.incr(key, by, def);
	}

	public long incr(String key, long by, long def, int expries) {
		return memcachedClient.incr(key, by, def, expries);
	}

	public long asyncIncr(String key, long by) {
		long value = -1;
		Future<Long> future = memcachedClient.asyncIncr(key, by);
		try {
			value = future.get(SpyMemcachedConstant.DEFAULT_TIMEOUT, SpyMemcachedConstant.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			future.cancel(false);
		}
		return value;
	}

	public long decr(String key, long by) {
		return memcachedClient.decr(key, by);
	}

	public long decr(String key, long by, long def) {
		return memcachedClient.decr(key, by, def);
	}

	public long decr(String key, long by, long def, int expries) {
		return memcachedClient.decr(key, by, def, expries);
	}

	public long asyncDecr(String key, long by) {
		long value = -1;
		Future<Long> future = memcachedClient.asyncDecr(key, by);
		try {
			value = future.get(SpyMemcachedConstant.DEFAULT_TIMEOUT, SpyMemcachedConstant.DEFAULT_TIMEUNIT);
		} catch (Exception e) {
			future.cancel(false);
		}
		return value;
	}

	public void flush() {
		memcachedClient.flush();
	}

}
