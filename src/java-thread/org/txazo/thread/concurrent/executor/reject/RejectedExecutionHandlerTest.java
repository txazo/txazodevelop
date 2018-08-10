package org.txazo.thread.concurrent.executor.reject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.RejectedExecutionHandler - 线程池的拒绝策略
 * 
 * @author txazo
 * 
 */
public class RejectedExecutionHandlerTest extends BaseTest {

	private static final int poolSize = 1;
	private static final int queueSize = 1;

	/**
	 * ThreadPoolExecutor.AbortPolicy
	 * 
	 * 任务添加到线程池被拒绝时，会抛出RejectedExecutionException异常
	 * ThreadPoolExecutor的默认RejectedExecutionHandler
	 */
	@Test
	public void testAbortPolicy() throws InterruptedException {
		ExecutorService pool = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize),
				new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < 10; i++) {
			pool.execute(new MyRunnable("Thread" + i));
		}
		pool.shutdown();
		Thread.sleep(2000);
	}

	/**
	 * ThreadPoolExecutor.CallerRunsPolicy
	 * 
	 * 任务添加到线程池被拒绝时，在当前正在运行的Thread线程池中执行被拒绝的任务
	 */
	@Test
	public void testCallerRunsPolicy() throws InterruptedException {
		ExecutorService pool = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize),
				new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 10; i++) {
			pool.execute(new MyRunnable("Thread" + i));
		}
		pool.shutdown();
		Thread.sleep(2000);
	}

	/**
	 * ThreadPoolExecutor.DiscardPolicy
	 * 
	 * 任务添加到线程池被拒绝时，线程池将丢弃被拒绝的任务，ThreadPoolExecutor默认处理方式
	 */
	@Test
	public void testDiscardPolicy() throws InterruptedException {
		ExecutorService pool = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize),
				new ThreadPoolExecutor.DiscardPolicy());
		for (int i = 0; i < 10; i++) {
			pool.execute(new MyRunnable("Thread" + i));
		}
		pool.shutdown();
		Thread.sleep(2000);
	}

	/**
	 * ThreadPoolExecutor.DiscardOldestPolicy
	 * 
	 * 任务添加到线程池被拒绝时，会放弃线程等待队列中最旧的未处理任务，然后将被拒绝的任务添加到线程等待队列中
	 */
	@Test
	public void testDiscardOldestPolicy() throws InterruptedException {
		ExecutorService pool = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		for (int i = 0; i < 10; i++) {
			pool.execute(new MyRunnable("Thread" + i));
		}
		pool.shutdown();
		Thread.sleep(2000);
	}

	@Test
	public void testTimeoutPolicy() throws InterruptedException {
		ExecutorService pool = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize),
				new TimeoutPolicy(2000));
		for (int i = 0; i < 10; i++) {
			pool.execute(new MyRunnable("Thread" + i));
		}
		Thread.sleep(10000);
		pool.shutdown();
	}

	/**
	 * 自定义RejectedExecutionHandler
	 * 
	 * 任务添加到线程池被拒绝时，timeout毫秒后重试添加任务，重试一次之后丢弃被拒绝的任务
	 */
	public class TimeoutPolicy implements RejectedExecutionHandler {

		private int timeout;
		private Map<Runnable, Integer> runnableMap = new HashMap<Runnable, Integer>();

		public TimeoutPolicy(int timeout) {
			this.timeout = timeout;
		}

		@Override
		public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
			if (!runnableMap.containsKey(r)) {
				runnableMap.put(r, 1);
				new Thread(r) {

					@Override
					public void run() {
						try {
							Thread.sleep(timeout);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						logger.info("repeat execute");
						executor.execute(r);
					}

				}.start();
			}
		}

	}

	public class MyRunnable implements Runnable {

		private String name;

		public MyRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			logger.info("{} {} is running", Thread.currentThread().getName(), name);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
