package org.txazo.thread.concurrent.executor.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.ThreadPoolExecutor
 * 
 * @author txazo
 * 
 */
public class ThreadPoolExecutorTest extends BaseTest {

	/**
	 * 参考
	 * 
	 * <pre>
	 * Executors.newSingleThreadExecutor
	 * Executors.newCachedThreadPool
	 * Executors.newFixedThreadPool
	 * </pre>
	 */
	@Test
	public void testThreadPoolExecutor() throws InterruptedException {
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(3);
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

		/**
		 * ThreadPoolExecutor参数
		 * 
		 * <pre>
		 * corePoolSize 线程池维护线程的最小数量
		 * maximumPoolSize 线程池维护线程的最大数量
		 * keepAliveTime 线程池维护线程所允许的空闲时间
		 * unit 线程池维护线程所允许的空闲时间的单位
		 * workQueue 线程池使用的缓冲队列
		 * threadFactory 创建线程的线程工厂
		 * handler 线程池对拒绝任务的处理策略
		 * </pre>
		 */
		ExecutorService threadPool = new ThreadPoolExecutor(2, 4, 0L, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);

		/**
		 * ThreadPoolExecutor.execute
		 * 
		 * <pre>
		 * 处理任务的优先级: corePoolSize > workQueue > maximumPoolSize
		 * 线程池中的线程数量小于corePoolSize，创建新的线程
		 * 线程池中的线程数量等于corePoolSize，缓冲队列workQueue未满，任务放入缓冲队列
		 * 线程池中的线程数量大于corePoolSize，缓冲队列workQueue满，小于maximumPoolSize，创建新的线程
		 * 线程池中的线程数量等于maximumPoolSize，缓冲队列workQueue满，通过handler指定的策略来处理此任务
		 * </pre>
		 */
		for (int i = 0; i < 10; i++) {
			threadPool.execute(new MyRunnable("Thread" + i));
		}

		Thread.sleep(10 * 1000);
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
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
