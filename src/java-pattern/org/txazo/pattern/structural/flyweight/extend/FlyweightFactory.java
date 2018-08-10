package org.txazo.pattern.structural.flyweight.extend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 享元模式扩展 - 享元工厂
 * 
 * <pre>
 * 1. 单例工厂
 * 2. 引用计数，统计每个享元被引用的次数
 * 3. 实现垃圾回收，回收垃圾享元
 * </pre>
 * 
 * @author txazo
 * 
 */
public class FlyweightFactory {

	private static FlyweightFactory instance = null;

	// 默认缓存失效时间
	private static final long DEFAULT_CACHE_TIME = 6 * 1000;

	private Map<String, Flyweight> map = new HashMap<String, Flyweight>();
	private Map<String, CacheConfiguration> cacheMap = new HashMap<String, CacheConfiguration>();
	private Map<String, Integer> countMap = new HashMap<String, Integer>();

	private FlyweightFactory() {
		Thread clearThread = new Thread(new ClearCacheThread());
		clearThread.start();
	}

	public static FlyweightFactory getInstance() {
		if (instance == null) {
			synchronized (FlyweightFactory.class) {
				if (instance == null) {
					instance = new FlyweightFactory();
				}
			}
		}
		return instance;
	}

	public synchronized int getUseTimes(String key) {
		Integer count = countMap.get(key);
		if (count == null) {
			count = 0;
		}
		return count;
	}

	public synchronized Flyweight getFlyweight(String key) {
		Flyweight flyweight = map.get(key);
		if (flyweight == null) {
			flyweight = new ConcreteFlyweight(key);
			map.put(key, flyweight);
			countMap.put(key, 1);

			CacheConfiguration configuration = new CacheConfiguration();
			configuration.setBeginTime(System.currentTimeMillis());
			configuration.setForever(false);
			configuration.setTimeout(DEFAULT_CACHE_TIME);
			cacheMap.put(key, configuration);
		} else {
			CacheConfiguration configuration = cacheMap.get(key);
			configuration.setBeginTime(System.currentTimeMillis());
			cacheMap.put(key, configuration);

			Integer count = countMap.get(key);
			countMap.put(key, count + 1);
		}
		System.out.println("获取缓存key:" + key + ",已使用" + countMap.get(key) + "次");
		return flyweight;
	}

	private synchronized void removeFlyweight(String key) {
		if (map.containsKey(key)) {
			map.remove(key);
			cacheMap.remove(key);
			countMap.remove(key);
			System.out.println("清除缓存key:" + key);
		}
	}

	private class ClearCacheThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				Set<String> tempSet = new HashSet<String>();
				Set<String> set = cacheMap.keySet();
				for (String key : set) {
					CacheConfiguration configuration = cacheMap.get(key);
					if (!configuration.isForever() && System.currentTimeMillis() - configuration.getBeginTime() >= configuration.getTimeout()) {
						tempSet.add(key);
					}
				}
				for (String key : tempSet) {
					removeFlyweight(key);
				}

				try {
					Thread.sleep(1000 * 5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
