package org.txazo.thread.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.CountDownLatch
 * 
 * @author txazo
 * 
 */
public class CountDownLatchTest extends BaseTest {

	@Test
	public void testCountDownLatch() throws InterruptedException {
		int size = 5;
		CountDownLatch latch = new CountDownLatch(size);
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < size; i++) {
			threadPool.execute(new WorkerThread(i, latch));
		}
		/** 等待计数器为零 */
		latch.await();
		logger.info("all works is done");
	}

	private class WorkerThread implements Runnable {

		private int index;
		private final CountDownLatch latch;

		private WorkerThread(int index, CountDownLatch latch) {
			this.index = index;
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
				logger.info("{} is done", index);
				/** 计数器减一 */
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
