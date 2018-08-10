package org.txazo.pattern.creational.builder.core;

/**
 * 具体的生成器B
 * 
 * @author txazo
 * 
 */
public class ConcreteBuilderB implements Builder {

	@Override
	public void buildHeader() {
		System.out.println("B Build Header");
	}

	@Override
	public void buildBody() {
		System.out.println("B Build Body");
	}

	@Override
	public void buildFooter() {
		System.out.println("B Build Footer");
	}

	@Override
	public Product getProduct() {
		return null;
	}

}
