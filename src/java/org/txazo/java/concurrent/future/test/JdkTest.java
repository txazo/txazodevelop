package org.txazo.java.concurrent.future.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.txazo.java.concurrent.future.jdk.RealData;

@RunWith(JUnit4.class)
public class JdkTest {

	@Test
	public void testFuture() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<String> future = executor.submit(new RealData("txazo"));
		int count = 0;
		while (!future.isDone()) {
			count++;
			if (count > 5) {
				future.cancel(true);
				break;
			} else {
				Thread.sleep(1000);
				System.out.println("Sleep\t" + count);
			}
		}

		if (future.isCancelled()) {
			System.out.println("Time Out");
		} else {
			String result = future.get();
			Assert.assertEquals("txazo", result);
		}
		executor.shutdown();
	}

	@Test
	public void testFutureGet() {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<String> future = executor.submit(new RealData("txazo"));
		String result = null;
		try {
			result = future.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		}
		Assert.assertEquals("txazo", result);
		executor.shutdown();
	}

}
