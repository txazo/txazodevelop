package org.txazo.pattern.behavior.state.core;

public class ConcreteStateA implements State {

	@Override
	public void handle(String parameter) {
		System.out.println("ConcreteStateA handle: " + parameter);
	}

}
