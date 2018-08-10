package org.txazo.thread.thread.lang;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.lang.Thread
 * 
 * @author txazo
 * 
 */
public class ThreadTest extends BaseTest {

	@Test
	public void testThread() throws InterruptedException {
		MyThread myThread = new MyThread();
		myThread.start();
		myThread.join();

		Runnable runnable = new MyRunnable();
		Thread thread = new Thread(runnable);
		thread.start();
		thread.join();
	}

	private class MyThread extends Thread {

		@Override
		public void run() {
			logger.info("{} is running", this.getName());
		}

	}

	private class MyRunnable implements Runnable {

		@Override
		public void run() {
			/** Thread.currentThread(), 当前线程 */
			logger.info("{} is running", Thread.currentThread().getName());
		}

	}

}
