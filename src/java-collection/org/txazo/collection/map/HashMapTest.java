package org.txazo.collection.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.HashMap
 * 
 * <pre>
 * 1) 非线程安全, key和value都可为null
 * 2) 结构: 数组(Entry[]) + 链表(Entry), 拉链法
 * 3) key-value对: HashMap.Entry(hash key value next)
 * 4) 原理: key - hash() - hash - index - Entry[index] - Entry - Entry.key - (key == Entry.key || key.equals(Entry.key))
 * 5) 扩容: 默认容量16, 容量超过capacity * loadFactor时扩容一倍(2的倍数), 创建时可设置初始容量(实际初始容量为不小于初始容量的最小2的n次幂), 避免频繁扩容
 * 6) Fail-Fast机制: 对HashMap进行迭代时, 除了迭代器的remove(), 其它任何对HashMap结构的修改都会抛出ConcurrentModificationException
 * 7) 重散列: 扩容时由于length扩大一倍, 需要重新计算index
 * </pre>
 */
public class HashMapTest extends BaseTest {

	@Test
	public void testHashMap() {
		String key = "key";
		String value = "value";
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		value = map.get(key);
		value = map.remove(key);
	}

	/**
	 * HashMap.indexFor(int h, int length)
	 * 
	 * return h & (length-1)
	 */
	@Test
	public void testIndexFor() {
		int hash = 353535;
		int length = 1 << 4;
		Assert.assertEquals(hash % length, hash & (length - 1));
	}

}
