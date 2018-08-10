package org.txazo.pattern.behavior.strategy.core;

public class AddStrategy implements Strategy {

	@Override
	public int calculate(int a, int b) {
		return a + b;
	}

}
