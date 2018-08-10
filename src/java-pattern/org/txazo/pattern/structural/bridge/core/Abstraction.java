package org.txazo.pattern.structural.bridge.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 桥接模式
 * 
 * <pre>
 * 1. 分离抽象与实现，各自独立变化
 * 2. 抽象持有实现的接口，可动态切换实现
 * 3. 实现：构造方法，简单工厂，IOC注入
 * </pre>
 * 
 * @author txazo
 * 
 */
public abstract class Abstraction {

	protected static Logger logger = LoggerFactory.getLogger("stdout");

	protected Implementor implementor;

	public Abstraction(Implementor implementor) {
		this.implementor = implementor;
	}

	public void setImplementor(Implementor implementor) {
		this.implementor = implementor;
	}

	public abstract void operation();

}
