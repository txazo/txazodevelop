package org.txazo.pattern.creational.singleton.holder;

/**
 * 内部类实现的单例模式
 * 
 * @author txazo
 * 
 */
public class Singleton {

	private Singleton() {
		/** 防止通过反射创建多个单例 */
		if (SingletonHolder.instance != null) {
			throw new IllegalArgumentException("Instance already exist");
		}
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}

	@Override
	public Singleton clone() {
		return getInstance();
	}

	private static class SingletonHolder {

		private static Singleton instance = new Singleton();

	}

}
