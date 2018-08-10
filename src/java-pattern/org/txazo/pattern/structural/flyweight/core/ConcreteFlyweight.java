package org.txazo.pattern.structural.flyweight.core;

/**
 * 享元模式 - 具体的享元实现对象
 * 
 * @author txazo
 * 
 */
public class ConcreteFlyweight implements Flyweight {

	// 内部状态
	private String state;

	public ConcreteFlyweight(String state) {
		this.state = state;
	}

	@Override
	public void operation(String externalState) {
		System.out.println("ConcreteFlyweight " + state + " operation " + externalState);
	}

}
