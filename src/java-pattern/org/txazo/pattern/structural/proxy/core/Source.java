package org.txazo.pattern.structural.proxy.core;

public class Source implements Sourcable {

	@Override
	public void operation() {
		System.out.println("Source operation");
	}

}
