package org.txazo.collection.traversal.core;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class TraversalTest extends BaseTest {

	/**
	 * Iterator遍历，Collection支持
	 */
	@Test
	public void testIterator() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}

	/**
	 * Enumeration遍历，Vector和Hashtable支持
	 */
	@Test
	public void testEnumeration() {
		Vector<Integer> vector = new Vector<Integer>();
		for (int i = 0; i < 10; i++) {
			vector.add(i);
		}

		// Enumeration遍历
		for (Enumeration<Integer> e = vector.elements(); e.hasMoreElements();) {
			System.out.println(e.nextElement());
		}
	}

}
