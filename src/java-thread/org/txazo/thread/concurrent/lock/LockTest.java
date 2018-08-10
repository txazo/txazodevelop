package org.txazo.thread.concurrent.lock;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.thread.annotation.ThreadSafe;

public class LockTest extends BaseTest {

	@Test
	public void testLock() throws InterruptedException {
		Counter counter = new Counter();
		Runnable runnable = new MyRunnable(counter);
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		Assert.assertEquals(2001, counter.incr());
	}

	/**
	 * 自定义可重入锁
	 */
	private class Lock {

		private volatile boolean locked = false;
		private volatile int count = 0;
		private volatile Thread lockedThread = null;

		public synchronized void lock() throws InterruptedException {
			Thread callingThread = Thread.currentThread();
			try {
				while (locked && lockedThread != callingThread) {
					wait();
				}
			} catch (InterruptedException e) {
				throw new InterruptedException();
			}
			locked = true;
			count++;
			lockedThread = callingThread;
		}

		public synchronized void unlock() {
			if (lockedThread == Thread.currentThread()) {
				count--;

				if (count == 0) {
					locked = false;
					notify();
				}
			}
		}

	}

	@ThreadSafe
	private class Counter {

		private int count = 0;
		private final Lock lock = new Lock();

		public int incr() {
			int newCount = 0;
			try {
				lock.lock();
				newCount = ++count;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return newCount;
		}

	}

	private class MyRunnable implements Runnable {

		private Counter counter;

		private MyRunnable(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			for (int i = 0; i < 1000; i++) {
				logger.info("{} {}", threadName, counter.incr());
			}
		}

	}

}
