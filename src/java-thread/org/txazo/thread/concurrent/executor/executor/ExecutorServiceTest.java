package org.txazo.thread.concurrent.executor.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.ExecutorService
 * 
 * @author txazo
 * 
 */
public class ExecutorServiceTest extends BaseTest {

	@Test
	public void testExecutorService() throws InterruptedException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(new MyCallable());

		int timeout = 1;
		String result = null;
		try {
			result = future.get(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			/** timeout时间过后未返回结果抛出TimeoutException */
		} finally {
			if (result != null) {
				logger.info("future get {}", result);
			} else {
				synchronized (future) {
					if (!future.isCancelled()) {
						boolean isCancelled = future.cancel(true);
						logger.info("future cancel {}", isCancelled);
					}
				}
			}
		}

		Thread.sleep(5000);

		executorService.shutdown();
	}

	private class MyCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			Thread.sleep(2000);
			logger.info("MyCallable exec");
			return "success";
		}

	}

}
