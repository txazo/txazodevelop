package org.txazo.pattern.creational.singleton.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap实现的单例模式
 * 
 * @author txazo
 * 
 */
public class Singleton {

	private static final String INSTANCE_KEY = "Singleton";
	private static final Map<String, Singleton> MAP = new HashMap<String, Singleton>();

	private Singleton() {
	}

	public static Singleton getInstance() {
		Singleton instance = (Singleton) MAP.get(INSTANCE_KEY);
		if (instance == null) {
			synchronized (MAP) {
				if (MAP.get(INSTANCE_KEY) == null) {
					MAP.put(INSTANCE_KEY, new Singleton());
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