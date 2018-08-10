package org.txazo.pattern.creational.builder.core;

/**
 * 具体的生成器A
 * 
 * @author txazo
 * 
 */
public class ConcreteBuilderA implements Builder {

	@Override
	public void buildHeader() {
		System.out.println("A Build Header");
	}

	@Override
	public void buildBody() {
		System.out.println("A Build Body");
	}

	@Override
	public void buildFooter() {
		System.out.println("A Build Footer");
	}

	@Override
	public Product getProduct() {
		return null;
	}

}
