package org.txazo.pattern.structural.flyweight.core;

/**
 * 享元模式 - 享元接口
 * 
 * @author txazo
 * 
 */
public interface Flyweight {

	/**
	 * operation
	 * 
	 * @param externalState 外部状态
	 */
	public void operation(String externalState);

}
