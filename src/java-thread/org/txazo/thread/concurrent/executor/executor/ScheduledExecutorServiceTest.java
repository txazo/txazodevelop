package org.txazo.thread.concurrent.executor.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.ScheduledExecutorService
 * 
 * @author txazo
 * 
 */
public class ScheduledExecutorServiceTest extends BaseTest {

	@Test
	public void testScheduledExecutorService() throws InterruptedException {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

		/** 延后执行一次 */
		executorService.schedule(new MyRunnable("schedule"), 1, TimeUnit.SECONDS);

		/** 延后并循环周期执行 */
		executorService.scheduleAtFixedRate(new MyRunnable("scheduleAtFixedRate"), 1, 1, TimeUnit.SECONDS);

		/** 延后并循环延后执行 */
		ScheduledFuture<?> future = executorService.scheduleWithFixedDelay(new MyRunnable("scheduleWithFixedDelay"), 1, 1, TimeUnit.SECONDS);

		Thread.sleep(5 * 1000);

		future.cancel(false);

		Thread.sleep(10 * 1000);
	}

	private class MyRunnable implements Runnable {

		private int times;
		private String name;

		private MyRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logger.info("MyRunnable {} exec {}", name, ++times);
		}

	}

}
