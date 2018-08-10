package org.txazo.pattern.structural.decorator.core;

public class ConcreteComponent implements Component {

	@Override
	public void operation() {
		System.out.println("ConcreteComponent execute...");
	}

}
