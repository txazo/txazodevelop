package org.txazo.thread.thread.lang;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 线程中断
 * 
 * <pre>
 * 1) interrupt(), 设置线程的interrupt状态, 并响应wait(), join(), sleep(), 抛出InterruptedException
 * 2) interrupted(), 检查线程的interrupt状态, 并重置interrupted状态
 * 3) isInterrupted(), 检查线程的interrupt状态
 * </pre>
 * 
 */
public class InterruptTest extends BaseTest {

	@Test
	public void testInterrupt() throws InterruptedException {
		CancelableTask task = new CancelableTask();
		Thread thread = new Thread(task);
		thread.start();
		Thread.sleep(1000);

		/** 中断线程 */
//		thread.interrupt();

		/** 取消任务 */
		task.calcel();

		thread.join();
	}

	public class CancelableTask implements Runnable {

		private volatile boolean calceled = false;

		@Override
		public void run() {
			try {
				while (true) {
					if (calceled || Thread.interrupted()) {
						throw new InterruptedException();
					}

					task();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		private void task() throws InterruptedException {
			try {
				for (int i = 0; i < 10; i++) {
					if (calceled || Thread.interrupted()) {
						throw new InterruptedException();
					}

					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				throw new InterruptedException();
			}
		}

		public void calcel() {
			calceled = true;
		}

	}

}
