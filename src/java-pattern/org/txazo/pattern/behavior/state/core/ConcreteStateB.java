package org.txazo.pattern.behavior.state.core;

public class ConcreteStateB implements State {

	@Override
	public void handle(String parameter) {
		System.out.println("ConcreteStateB handle: " + parameter);
	}

}
