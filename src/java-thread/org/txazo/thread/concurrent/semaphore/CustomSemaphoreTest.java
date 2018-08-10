package org.txazo.thread.concurrent.semaphore;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.thread.annotation.ThreadSafe;

/**
 * 自定义信号量
 * 
 * @author txazo
 * 
 */
public class CustomSemaphoreTest extends BaseTest {

	@Test
	public void testCustomSemaphore() throws InterruptedException {
		Counter counter = new Counter();
		CounterThread counterThread = new CounterThread(counter);
		Thread t1 = new Thread(counterThread);
		Thread t2 = new Thread(counterThread);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		Assert.assertEquals(2000, counter.getCount().intValue());
	}

	/**
	 * 自定义信号量
	 * 
	 * @author txazo
	 * 
	 */
	@ThreadSafe
	public class Semaphore {

		private int singals = 0;
		private int bound = 0;

		private Semaphore(int bound) {
			if (bound < 0) {
				throw new IllegalArgumentException("Illegal bound: " + bound);
			}
			this.bound = bound;
		}

		public synchronized void acquire() {
			while (singals == bound) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			singals++;
		}

		public synchronized void release() {
			singals--;
			notify();
		}

	}

	@ThreadSafe
	public class Counter {

		private int count = 0;
		private Semaphore semaphore = new Semaphore(1);

		public Integer getCount() {
			try {
				semaphore.acquire();
				return count;
			} finally {
				semaphore.release();
			}
		}

		public Integer incr() {
			try {
				semaphore.acquire();
				return ++count;
			} finally {
				semaphore.release();
			}
		}

	}

	public class CounterThread implements Runnable {

		private Counter counter;

		private CounterThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			for (int i = 0; i < 1000; i++) {
				logger.info("{} getCount {} incr {}", threadName, counter.getCount(), counter.incr());
			}
		}

	}

}
