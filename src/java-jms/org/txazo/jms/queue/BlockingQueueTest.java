package org.txazo.jms.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.txazo.test.base.BaseTest;

public class BlockingQueueTest extends BaseTest {

	private static Logger logger = Logger.getLogger(BlockingQueueTest.class);
	private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

	public static void main(String[] args) throws InterruptedException {
		new Thread(new QueueOfferThread()).start();
		new Thread(new QueuePullThread()).start();

		Thread.sleep(100000);
	}

	private static class QueueOfferThread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				blockingQueue.offer(String.valueOf(i));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class QueuePullThread implements Runnable {

		@Override
		public void run() {
			String value = null;
			while (true) {
				if ((value = blockingQueue.poll()) != null) {
					logger.info(value);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
