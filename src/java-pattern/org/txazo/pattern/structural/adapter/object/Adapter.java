package org.txazo.pattern.structural.adapter.object;

/**
 * 对象适配器
 * 
 * <pre>
 * 1. 转换接口，复用功能
 * 2. 对象组合
 * 3. Adaptee到Target的适配器
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Adapter implements Target {

	private Adaptee adaptee;

	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();
	}

}
