package org.txazo.pattern.structural.flyweight.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式 - 享元工厂
 * 
 * <pre>
 * 1. 运用共享技术有效地支持大量细粒度的对象
 * 2. 享元工厂可实现为单例
 * 3. 分离变与不变，内部状态是不变的，外部状态是可变的
 * 4. 缓存和共享的数据是享元的内部状态
 * 5. 减少对象数量，节省内存空间
 * 6. 分离与共享
 * 7. 应用：
 *        常量池中的字符串常量
 * </pre>
 * 
 * @author txazo
 * 
 */
public class FlyweightFactory {

	private static FlyweightFactory instance = new FlyweightFactory();

	private Map<String, Flyweight> map = new HashMap<String, Flyweight>();

	private FlyweightFactory() {
	}

	public Flyweight getFlyweight(String state) {
		Flyweight flyweight = map.get(state);
		if (flyweight == null) {
			flyweight = new ConcreteFlyweight(state);
			map.put(state, flyweight);
		}
		return flyweight;
	}

	public static FlyweightFactory getInstance() {
		return instance;
	}

}
