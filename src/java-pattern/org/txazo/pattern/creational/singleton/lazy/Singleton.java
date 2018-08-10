package org.txazo.pattern.creational.singleton.lazy;

/**
 * 懒汉式单例模式
 * 
 * <pre>
 * 1) 延迟加载
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Singleton {

	private static Singleton instance;

	private Singleton() {
		/** 防止通过反射创建多个单例 */
		if (instance != null) {
			throw new IllegalArgumentException("Instance already exist");
		}
	}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	@Override
	public Singleton clone() {
		return getInstance();
	}

}
