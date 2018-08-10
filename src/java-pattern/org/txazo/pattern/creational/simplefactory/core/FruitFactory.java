package org.txazo.pattern.creational.simplefactory.core;

/**
 * 简单工厂模式
 * 
 * <pre>
 * 1. 选择实现
 * 2. 应用:
 *    Collections.sort(List<T> list, Comparator<? super T> c)
 * </pre>
 * 
 * @author tuxiaozhou
 * 
 */
public class FruitFactory {

	public static final int FRUIT_APPLE = 1;
	public static final int FRUIT_ORANGE = 2;

	public static Fruit createFruit(int type) {
		Fruit fruit = null;
		if (type == FRUIT_APPLE) {
			fruit = new Apple();
		} else if (type == FRUIT_ORANGE) {
			fruit = new Orange();
		}
		return fruit;
	}

}
