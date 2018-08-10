package org.txazo.thread.concurrent.executor.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 定制的ThreadPoolExecutor
 * 
 * @author txazo
 * 
 */
public class ThreadPoolExecutorCustomTest extends BaseTest {

	@Test
	public void testThreadPoolExecutor() throws InterruptedException {
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(20);
		ThreadFactory threadFactory = new CustomThreadFactory("pool");
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		CustomThreadPool threadPool = new CustomThreadPool(20, 40, 0L, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);

		for (int i = 0; i < 100; i++) {
			threadPool.execute(new MyRunnable("Thread" + i));
		}

		Thread.sleep(10 * 1000);
	}

	/**
	 * 定制ThreadPoolExecutor
	 */
	public class CustomThreadPool extends ThreadPoolExecutor {

		private final AtomicInteger currentCount = new AtomicInteger();
		private final AtomicInteger finishedCount = new AtomicInteger();
		private final AtomicInteger totalCount = new AtomicInteger();

		public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
				ThreadFactory threadFactory, RejectedExecutionHandler handler) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
		}

		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			currentCount.incrementAndGet();
			totalCount.incrementAndGet();
			super.beforeExecute(t, r);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			logger.info("current task {}\tfinished task {}\ttotal task {}", currentCount.decrementAndGet(), finishedCount.incrementAndGet(),
					totalCount.get());
			super.afterExecute(r, t);
		}

	}

	/**
	 * 定制ThreadFactory
	 */
	public class CustomThreadFactory implements ThreadFactory {

		private final String poolName;

		public CustomThreadFactory(String poolName) {
			this.poolName = poolName;
		}

		@Override
		public Thread newThread(Runnable r) {
			return new CustomThread(r, poolName);
		}

	}

	/**
	 * 定制Thread
	 */
	public static class CustomThread extends Thread {

		private static final AtomicInteger created = new AtomicInteger();

		public CustomThread(Runnable r, String poolName) {
			super(r, poolName + "-" + created.incrementAndGet());
			logger.info("New CustomThread {}", getName());
		}

		@Override
		public void run() {
			super.run();
		}

	}

	private class MyRunnable implements Runnable {

		private String name;

		private MyRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			logger.info("{} exec", name);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
