package org.txazo.pattern.structural.bridge.core;

public class AAbstraction extends Abstraction {

	public AAbstraction(Implementor implementor) {
		super(implementor);
	}

	@Override
	public void operation() {
		logger.info("AAbstraction " + implementor.implement());
	}

}
