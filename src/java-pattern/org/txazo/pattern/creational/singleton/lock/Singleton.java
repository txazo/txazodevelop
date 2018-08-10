package org.txazo.pattern.creational.singleton.lock;

/**
 * 双重锁单例模式
 * 
 * @author txazo
 * 
 */
public class Singleton {

	private static Singleton instance = null;

	private Singleton() {
		/** 防止通过反射创建多个单例 */
		if (instance != null) {
			throw new IllegalArgumentException("Instance already exist");
		}
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	@Override
	public Singleton clone() {
		return getInstance();
	}

}
