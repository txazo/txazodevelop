package org.txazo.pattern.structural.decorator.core;

/**
 * 装饰器A
 * 
 * @author txazo
 * 
 */
public class ConcreteDecoratorA extends Decorator {

	public ConcreteDecoratorA(Component component) {
		super(component);
	}

	private void addOperation() {
		System.out.println("ConcreteDecoratorA 装饰...");
	}

	@Override
	public void operation() {
		System.out.println("ConcreteDecoratorA 装饰前");
		super.operation();
		addOperation();
		System.out.println("ConcreteDecoratorA 装饰后");
	}

}
