package org.txazo.java.concurrent.future.jdk;

import java.util.Random;
import java.util.concurrent.Callable;

public class RealData implements Callable<String> {

	private String query;
	private Random random = new Random();

	public RealData(String query) {
		this.query = query;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000 * random.nextInt(10));
		return query != null ? query : null;
	}

}
