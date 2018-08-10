package org.txazo.pattern.creational.prototype.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型管理器
 * 
 * @author tuxiaozhou
 * 
 */
public class PrototypeManager {

	private static Map<String, Object> map = new HashMap<String, Object>();

	private PrototypeManager() {
	}

	public static Prototype getClone(String prototypeName) {
		Prototype clone = null;
		Prototype prototype = (Prototype) map.get(prototypeName);
		if (prototype != null) {
			clone = prototype.clone();
		}
		return clone;
	}

	public static Prototype getPrototype(String prototypeName) {
		return (Prototype) map.get(prototypeName);
	}

	public static synchronized void setPrototype(String prototypeName, Prototype prototype) {
		map.put(prototypeName, prototype);
	}

	public static synchronized void removePrototype(String prototypeName) {
		map.remove(prototypeName);
	}

}