package org.txazo.thread.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.CyclicBarrier
 * 
 * @author txazo
 * 
 */
public class CyclicBarrierTest extends BaseTest {

	@Test
	public void testCyclicBarrier() throws InterruptedException {
		int size = 5;
		CyclicBarrier barrier = new CyclicBarrier(size);
		Thread thread = null;
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < size; i++) {
			thread = new Thread(new WorkerThread(i, barrier));
			threads.add(thread);
			thread.start();
		}

		for (Thread t : threads) {
			t.join();
		}
	}

	private class WorkerThread implements Runnable {

		private int index;
		private final CyclicBarrier barrier;

		private WorkerThread(int index, CyclicBarrier barrier) {
			this.index = index;
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
				logger.info("{} is ready", index);
				barrier.await();
				logger.info("{} is done", index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

	}

}
