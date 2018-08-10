package org.txazo.collection.list.traverse;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * LinkedList遍历
 * 
 * @author txazo
 * 
 */
public class LinkedListTraverseTest extends BaseTest {

	@Test
	public void testListTraverse() {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}

		// LinkedList遍历方式一，效率低
		for (int i = 0, size = list.size(); i < size; i++) {
			System.out.println(list.get(i));
		}

		// LinkedList遍历方式二，内部调用Iterator，效率高
		for (Integer i : list) {
			System.out.println(i);
		}

		// LinkedList遍历方式三，效率高
		for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}

		// LinkedList遍历方式四，效率高
		Iterator<Integer> i = list.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}

}
