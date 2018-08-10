package org.txazo.java.concurrent.future.pattern;

public class FutureData implements Data {

	private RealData realData = null;
	private boolean isReady = false;

	public synchronized void setRealData(RealData realData) {
		if (!isReady) {
			this.realData = realData;
			isReady = true;
			notify();
		}
	}

	@Override
	public synchronized String getResult() {
		while (!isReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.getResult();
	}

}
