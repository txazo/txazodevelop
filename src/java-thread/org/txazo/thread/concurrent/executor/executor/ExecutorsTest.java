package org.txazo.thread.concurrent.executor.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.Executors
 * 
 * @author txazo
 * 
 */
public class ExecutorsTest extends BaseTest {

	/**
	 * 单个线程的线程池
	 */
	@Test
	public void testSingleThreadExecutor() throws InterruptedException {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			singleThreadPool.execute(new MyRunnable("Thread" + i, 500));
		}
		Thread.sleep(6000);
		singleThreadPool.shutdown();
	}

	/**
	 * 线程数动态调整的线程池
	 */
	@Test
	public void testCachedThreadPool() throws InterruptedException {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			cachedThreadPool.execute(new MyRunnable("Thread" + i, 1000));
		}
		Thread.sleep(2000);
		cachedThreadPool.shutdown();
	}

	/**
	 * 线程数固定的线程池
	 */
	@Test
	public void testFixedThreadPool() throws InterruptedException {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			fixedThreadPool.execute(new MyRunnable("Thread" + i, 2000));
		}
		Thread.sleep(5000);
		fixedThreadPool.shutdown();
	}

	/** 单个线程的可调度线程池 */
	@Test
	public void testSingleThreadScheduledExecutor() throws InterruptedException {
		ScheduledExecutorService singleScheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
		/** 延后执行 */
		singleScheduledThreadPool.schedule(new MyRunnable("Thread0", 100), 1, TimeUnit.SECONDS);
		/** 延后并循环周期执行 */
		singleScheduledThreadPool.scheduleAtFixedRate(new MyRunnable("Thread1", 100), 3, 1, TimeUnit.SECONDS);
		/** 延后并循环延后执行 */
		singleScheduledThreadPool.scheduleWithFixedDelay(new MyRunnable("Thread2", 100), 5, 2, TimeUnit.SECONDS);
		Thread.sleep(60000);
	}

	/** 线程数动态调整的可调度线程池 */
	@Test
	public void testScheduledThreadPool() throws InterruptedException {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		/** 延后执行 */
		scheduledThreadPool.schedule(new MyRunnable("Thread0", 100), 1, TimeUnit.SECONDS);
		/** 延后并循环周期执行 */
		scheduledThreadPool.scheduleAtFixedRate(new MyRunnable("Thread1", 300), 3, 1, TimeUnit.SECONDS);
		/** 延后并循环延后执行 */
		scheduledThreadPool.scheduleWithFixedDelay(new MyRunnable("Thread2", 300), 5, 2, TimeUnit.SECONDS);
		Thread.sleep(60000);
	}

	private class MyRunnable implements Runnable {

		private String name;
		private int sleepTime;

		public MyRunnable(String name, int sleepTime) {
			this.name = name;
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			logger.info("{} is running", name);

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
