package org.txazo.schedule.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.txazo.test.base.SpringBaseTest;

/**
 * Spring TaskExecutor
 * 
 * @author txazo
 * 
 */
public class SpringTaskExecutorTest extends SpringBaseTest {

	@Autowired
	private TaskExecutor springTaskExecutor;

	@Test
	public void testSpringTaskExecutor() throws InterruptedException {
		for (int i = 0; i < 30; i++) {
			springTaskExecutor.execute(new TaskThread("Task" + i));
		}

		Thread.sleep(60 * 1000);
	}

	private class TaskThread implements Runnable {

		private String name;

		private TaskThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logger.info("{}", name);
		}

	}

}
