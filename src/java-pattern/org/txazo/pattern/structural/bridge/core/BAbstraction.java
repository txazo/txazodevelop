package org.txazo.pattern.structural.bridge.core;

public class BAbstraction extends Abstraction {

	public BAbstraction(Implementor implementor) {
		super(implementor);
	}

	@Override
	public void operation() {
		logger.info("BAbstraction " + implementor.implement());
	}

}
