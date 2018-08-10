package org.txazo.pattern.behavior.template.core;

public class ConcreteTemplate extends AbstractTemplate {

	@Override
	public void abstractMethod() {
		System.out.println("abstractMethod");
	}

	@Override
	protected void hookMethod() {
		System.out.println("hookMethod");
	}

	@Override
	protected Object createObject() {
		System.out.println("createObject");
		return null;
	}

}
