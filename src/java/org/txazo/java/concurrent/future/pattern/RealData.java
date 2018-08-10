package org.txazo.java.concurrent.future.pattern;

public class RealData implements Data {

	private String result = null;

	public RealData(String query) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result = query != null ? query : null;
	}

	@Override
	public String getResult() {
		return result;
	}

}
