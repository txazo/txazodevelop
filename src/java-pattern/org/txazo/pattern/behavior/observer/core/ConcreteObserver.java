package org.txazo.pattern.behavior.observer.core;

public class ConcreteObserver implements Observer {

	private String name;

	public ConcreteObserver(String name) {
		this.name = name;
	}

	@Override
	public void update(Subject subject) {
		System.out.println("Subject change Observer " + name);
	}

}
