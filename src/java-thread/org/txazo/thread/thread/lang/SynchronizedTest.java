package org.txazo.thread.thread.lang;

import java.util.Random;

import org.junit.Test;
import org.terracotta.statistics.jsr166e.ThreadLocalRandom;
import org.txazo.test.base.BaseTest;

/**
 * Synchronized
 * 
 * @author txazo
 * 
 */
public class SynchronizedTest extends BaseTest {

	@Test
	public void testSynchronized() throws InterruptedException {
		MyRunnable runnable = new MyRunnable();
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private static class Synchronized {

		private static Object lock = new Object();
		private static Synchronized instance = null;

		private int count = 0;
		private static int staticCount = 0;

		private Synchronized() {
		}

		/**
		 * 同步方法，this对象锁
		 */
		public synchronized int getCount() {
			return count;
		}

		public void setCount(int count) {
			/** 同步块，this对象锁 */
			synchronized (this) {
				this.count = count;
			}
		}

		/**
		 * 同步方法，Class对象锁
		 */
		public synchronized static int getStaticCount() {
			return staticCount;
		}

		public static void setStaticCount(int staticCount) {
			/** 同步块，Class对象锁 */
			synchronized (Synchronized.class) {
				Synchronized.staticCount = staticCount;
			}
		}

		public static Synchronized getInstance() {
			if (instance == null) {
				/** 同步块，对象锁 */
				synchronized (lock) {
					if (instance == null) {
						instance = new Synchronized();
					}
				}
			}
			return instance;
		}

	}

	private class MyRunnable implements Runnable {

		@Override
		public void run() {
			Random random = ThreadLocalRandom.current();
			Synchronized synchronizd = Synchronized.getInstance();
			for (int i = 0; i < 10; i++) {
				synchronizd.setCount(random.nextInt(10));
				Synchronized.setStaticCount(random.nextInt(10));
				logger.info("{} Synchronizd count {} staticCount {}", Thread.currentThread().getName(), synchronizd.getCount(), Synchronized.getStaticCount());

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
