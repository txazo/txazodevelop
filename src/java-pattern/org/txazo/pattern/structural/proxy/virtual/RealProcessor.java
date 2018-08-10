package org.txazo.pattern.structural.proxy.virtual;

public class RealProcessor implements Processor {

	public RealProcessor() {
		System.out.println("create RealProcessor");
	}

	@Override
	public void process() {
		System.out.println("RealProcessor process");
	}

}
