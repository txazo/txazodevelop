package org.txazo.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * EhCache
 * 
 * @author txazo
 * 
 */
public class EhCache {

	private static final String configFile = "src/resource/ehcache.xml";

	private static CacheManager manager;

	static {
		manager = CacheManager.newInstance(configFile);
	}

	private static Cache getCache(String cacheName) {
		return manager.getCache(cacheName);
	}

	public static void put(String cacheName, String key, Object value) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			Element element = new Element(key, value);
			cache.put(element);
		}
	}

	public static Object get(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			return element != null ? element.getObjectValue() : null;
		}
		return null;
	}

	public static void remove(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.remove(key);
		}
	}

	public static void shutdown() {
		manager.shutdown();
	}

}
