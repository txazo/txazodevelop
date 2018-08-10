package org.txazo.pattern.structural.decorator.core;

/**
 * 装饰器B
 * 
 * @author txazo
 * 
 */
public class ConcreteDecoratorB extends Decorator {

	public ConcreteDecoratorB(Component component) {
		super(component);
	}

	private void addOperation() {
		System.out.println("ConcreteDecoratorB 装饰...");
	}

	@Override
	public void operation() {
		System.out.println("ConcreteDecoratorB 装饰前");
		super.operation();
		addOperation();
		System.out.println("ConcreteDecoratorB 装饰后");
	}

}
