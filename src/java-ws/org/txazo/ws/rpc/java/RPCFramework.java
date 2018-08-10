package org.txazo.ws.rpc.java;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

/**
 * 简单的RPC框架
 */
public class RPCFramework implements RPC {

	private static final Lock lock = new ReentrantLock(false);
	private static final Map<String, RPCThread> serviceMap = new HashedMap<String, RPCThread>();

	/**
	 * 注册服务
	 * 
	 * @param service 服务实现
	 * @param port 服务端口
	 * @throws Exception
	 */
	@Override
	public void register(final Object service, final int port) throws Exception {
		if (service == null) {
			throw new IllegalArgumentException("Service Instance is null");
		}
		if (port <= 0 || port > 65535) {
			throw new IllegalArgumentException("Invalid port " + port);
		}

		String className = service.getClass().getName();
		try {
			lock.lock();
			if (!serviceMap.containsKey(className)) {
				RPCThread rpcThread = new RPCThread(service, port);
				serviceMap.put(className, rpcThread);
				new Thread(rpcThread).start();
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 取消注册服务
	 * 
	 * @param implClass 服务实现类型
	 */
	@Override
	public void unRegister(Class<?> implClass) {
		if (implClass == null) {
			throw new IllegalArgumentException("Class is null");
		}

		String className = implClass.getName();
		if (serviceMap.containsKey(className)) {
			RPCThread rpcThread = serviceMap.get(className);
			rpcThread.stop();
		}
	}

	/**
	 * 服务代理
	 * 
	 * @param <T> 接口泛型
	 * @param interfaceClass 接口类型
	 * @param host 服务器主机名
	 * @param port 服务器端口
	 * @return 远程服务
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProxy(final Class<T> interfaceClass, final String host, final int port) throws Exception {
		if (interfaceClass == null) {
			throw new IllegalArgumentException("Interface Class is null");
		}
		if (!interfaceClass.isInterface()) {
			throw new IllegalArgumentException(interfaceClass.getName() + " must be Interface Class");
		}
		if (StringUtils.isBlank(host)) {
			throw new IllegalArgumentException("Host is null");
		}
		if (port <= 0 || port > 65535) {
			throw new IllegalArgumentException("Invalid port " + port);
		}

		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass }, new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket = new Socket(host, port);
				try {
					ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
					try {
						/** 方法名 */
						output.writeUTF(method.getName());
						/** 方法参数类型 */
						output.writeObject(method.getParameterTypes());
						/** 方法参数 */
						output.writeObject(args);
						ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
						try {
							Object result = input.readObject();
							if (result instanceof Throwable) {
								throw (Throwable) result;
							}
							return result;
						} finally {
							input.close();
						}
					} finally {
						output.close();
					}
				} finally {
					socket.close();
				}
			}

		});
	}

	private static class RPCThread implements Runnable {

		private Object service;
		private int port;
		private ServerSocket server;
		private volatile boolean isStop = false;
		private volatile Thread blinker;

		public RPCThread(Object service, int port) {
			this.service = service;
			this.port = port;
		}

		public synchronized void stop() {
			isStop = true;
			if (blinker != null) {
				blinker.interrupt();
			}
		}

		@Override
		public void run() {
			blinker = Thread.currentThread();
			try {
				server = new ServerSocket(port);
				while (!isStop) {
					final Socket socket = server.accept();
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								try {
									ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
									try {
										String methodName = input.readUTF();
										Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
										Object[] args = (Object[]) input.readObject();
										ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
										try {
											Method method = service.getClass().getMethod(methodName, parameterTypes);
											Object result = method.invoke(service, args);
											output.writeObject(result);
										} catch (Throwable t) {
											output.writeObject(t);
										} finally {
											output.close();
										}
									} finally {
										input.close();
									}
								} finally {
									socket.close();
								}
							} catch (Exception e) {
							}
						}

					}).start();
				}
			} catch (Exception e) {
			}
		}

	}

}
