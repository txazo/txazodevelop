package org.txazo.pattern.structural.proxy.protect;

public class RealHandler implements Handler {

	@Override
	public void handle(String user) {
		System.out.println(user + " handle");
	}

}
