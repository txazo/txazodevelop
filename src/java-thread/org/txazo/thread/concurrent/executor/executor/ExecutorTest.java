package org.txazo.thread.concurrent.executor.executor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.Executor
 * 
 * @author txazo
 * 
 */
public class ExecutorTest extends BaseTest {

	@Test
	public void testExecutor() throws InterruptedException {
		Executor executor = new SingleExecutor();
		for (int i = 0; i < 5; i++) {
			executor.execute(new MyRunnable());
		}

		Thread.sleep(20 * 1000);
	}

	/**
	 * 自定义Executor, 一次只执行单个线程的Executor
	 */
	private class SingleExecutor implements Executor {

		private final CoreThread coreThread;
		private final Queue<Runnable> tasks = new ArrayDeque<Runnable>();

		public SingleExecutor() {
			coreThread = new CoreThread();
			new Thread(coreThread).start();
		}

		@Override
		public void execute(Runnable command) {
			tasks.offer(command);
			logger.info("SingleExecutor add Runnable");
			coreThread.assign();
		}

		private class CoreThread implements Runnable {

			private volatile boolean available = true;

			@Override
			public void run() {
				while (true) {
					await();
				}
			}

			private synchronized void await() {
				try {
					while (tasks.isEmpty()) {
						available = false;
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Runnable runnable = tasks.poll();
				try {
					runnable.run();
				} finally {
				}
			}

			private synchronized void assign() {
				if (!available) {
					available = true;
					notify();
				}
			}

		}

	}

	private class MyRunnable implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logger.info("MyRunnable exec");
		}

	}

}
