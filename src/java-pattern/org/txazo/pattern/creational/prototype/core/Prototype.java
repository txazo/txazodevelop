package org.txazo.pattern.creational.prototype.core;

/**
 * 原型模式
 * 
 * <pre>
 * 1. 克隆生成对象
 * 2. 拷贝原型，创建对象
 * 3. 应用:
 *    java.lang.Cloneable
 * </pre>
 * 
 * @author tuxiaozhou
 * 
 */
public interface Prototype {

	public Prototype clone();

}
