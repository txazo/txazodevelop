package org.txazo.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.ArrayList
 * 
 * <pre>
 * 1) 线程不安全
 * 2) 内部实现: Object[] elementData
 * 3) 动态增长数组，每次扩容1/2
 * 4) 可指定初始化容量: ArrayList(int initialCapacity)
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ArrayListTest extends BaseTest {

	@Test
	public void testArrayList() {
		List<String> list = new ArrayList<String>(10);

		list.add("txazo"); // O(1)
		list.get(0); // O(1)
		list.set(0, "txazo"); // O(1)

		list.add(0, "txazo"); // System.arraycopy
		list.remove(0); // System.arraycopy
		list.remove("txazo"); // O(n) + System.arraycopy

		list.contains(1); // O(n)

		list.toArray(); // System.arraycopy
	}

	/**
	 * ArrayList遍历
	 * 
	 * <pre>
	 * 1) 多线程下遍历要做同步操作
	 * </pre>
	 */
	@Test
	public void testArrayListTraversal() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		// ArrayList遍历方式一，性能最高
		for (int i = 0, size = list.size(); i < size; i++) {
			logger.info("{}", list.get(i));
		}

		// ArrayList遍历方式二，内部调用Iterator
		for (Integer i : list) {
			logger.info("{}", i);
		}

		// ArrayList遍历方式三
		for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
			logger.info("{}", i.next());
		}

		// ArrayList遍历方式四
		Iterator<Integer> i = list.iterator();
		while (i.hasNext()) {
			logger.info("{}", i.next());
		}
	}

}
