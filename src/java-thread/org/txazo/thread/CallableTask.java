package org.txazo.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableTask implements Callable<String> {

	private int id;

	public CallableTask(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(100 * (id % 10));
		return "CallableTask\t" + id;
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException, TimeoutException {
		ExecutorService exec = Executors.newFixedThreadPool(100);
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 100; i++) {
			results.add(exec.submit(new CallableTask(i)));
		}

		for (Future<String> future : results) {
			if (future.isDone()) {
				System.out.println(future.get(1, TimeUnit.SECONDS));
			} else {
				System.out.println("noe done");
			}
		}

		exec.shutdown();
	}

}
