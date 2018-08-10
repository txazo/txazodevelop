package org.txazo.thread.concurrent.collection.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.ConcurrentHashMap
 * 
 * <pre>
 * 1) 线程安全, 支持高并发(完全并发的读和一定程度并发的写)
 * 2) 结构: Segment<K, V>[] - HashEntry<K, V>[] - HashEntry(hash key value next)
 * 3) 过程: key - hash - Segment - HashEntry - HashEntry.key
 * 4) Segment: 默认16个, 继承自ReentrantLock, 类似于HashMap(数组 + 链表)
 * 5) 原理: 锁分离, 锁分段, 减小锁粒度
 * 6) 读取: 读取时不加锁, Segment<K, V>[]和HashEntry<K, V>[]都为volatile类型
 * 7) 修改: 修改时加锁Segment, 允许多个修改操作并发进行
 * 8) 迭代: 弱一致迭代器, 不会产生ConcurrentModificationException
 * 9) 补充: size()和containsValue()会锁住整个ConcurrentHashMap, 避免频繁调用
 * </pre>
 */
public class ConcurrentHashMapTest extends BaseTest {

	@Test
	public void testConcurrentHashMap() {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		map.clear();
	}

}
