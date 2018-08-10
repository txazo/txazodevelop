package org.txazo.pattern.structural.adapter.clazz;

/**
 * 类适配器
 * 
 * <pre>
 * 1. 转换接口，复用功能
 * 2. 继承
 * 3. Adaptee到Target的适配器
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Adapter extends Adaptee implements Target {

	@Override
	public void request() {
		this.specificRequest();
	}

}
