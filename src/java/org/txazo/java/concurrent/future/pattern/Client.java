package org.txazo.java.concurrent.future.pattern;

public class Client {

	public Data request(final String query) {
		final FutureData futureData = new FutureData();

		new Thread() {

			@Override
			public void run() {
				RealData realData = new RealData(query);
				futureData.setRealData(realData);
			}

		}.start();

		return futureData;
	}

}
