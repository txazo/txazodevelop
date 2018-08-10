package org.txazo.pattern.creational.singleton.hungry;

/**
 * 饿汉式单例模式
 * 
 * <pre>
 * 1) 一个类只有一个实例
 * 2) 提供一个全局的获取实例的接口
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Singleton {

	private static Singleton instance = new Singleton();

	private Singleton() {
		/** 防止通过反射创建多个单例 */
		if (instance != null) {
			throw new IllegalArgumentException("Instance already exist");
		}
	}

	public static Singleton getInstance() {
		return instance;
	}

	@Override
	public Singleton clone() {
		return getInstance();
	}

}
