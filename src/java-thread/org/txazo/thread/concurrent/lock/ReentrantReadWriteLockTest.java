package org.txazo.thread.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.locks.ReentrantReadWriteLock - 读写锁
 * 
 * <pre>
 * 读操作: 没有线程正在写操作，且没有线程在请求写操作
 * 写操作: 没有线程正在读写操作
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ReentrantReadWriteLockTest extends BaseTest {

	@Test
	public void testReentrantReadWriteLock() throws InterruptedException {
		Runnable myThread = new MyThread();
		Thread t1 = new Thread(myThread);
		Thread t2 = new Thread(myThread);

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private class LockedMap {

		private final Map<String, Object> map = new HashMap<String, Object>();
		private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		private final Lock readLock = lock.readLock();
		private final Lock writeLock = lock.writeLock();

		public Object get(String key) {
			readLock.lock();
			try {
				logger.info("get " + key);
				return map.get(key);
			} finally {
				readLock.unlock();
			}
		}

		public Object put(String key, Object value) {
			writeLock.lock();
			try {
				logger.info("put key: " + key + ", value: " + value);
				return map.put(key, value);
			} finally {
				writeLock.unlock();
			}
		}

	}

	private class MyThread implements Runnable {

		private LockedMap map = new LockedMap();

		@Override
		public void run() {
			String s = null;
			for (int i = 0; i < 100; i++) {
				s = String.valueOf(i);
				if (i % 2 == 0) {
					map.get(s);
				} else {
					map.put(s, s);
				}
			}
		}

	}

}
