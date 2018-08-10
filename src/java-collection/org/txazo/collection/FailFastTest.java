package org.txazo.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * fail-fast机制
 * 
 * @author txazo
 * 
 */
public class FailFastTest extends BaseTest {

	@Test
	public void testListFailFast() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		Integer number = null;
		for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
			number = i.next();
			System.out.println(number);
			if (number == 5) {
				// list.remove(number);
				i.remove();
			}
		}

		for (Iterator<Integer> i = list.iterator(); i.hasNext();) {
			number = i.next();
			System.out.println(number);
		}
	}

	@Test
	public void testSetFailFast() {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < 10; i++) {
			set.add(i);
		}

		Integer number = null;
		for (Iterator<Integer> i = set.iterator(); i.hasNext();) {
			number = i.next();
			System.out.println(number);
			if (number == 5) {
				set.remove(number);
			}
		}
	}

	@Test
	public void testMapFailFast() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 10; i++) {
			map.put(i, i * i);
		}

		Integer key = null;
		for (Iterator<Integer> i = map.keySet().iterator(); i.hasNext();) {
			key = i.next();
			System.out.println(map.get(key));
			if (key == 5) {
				map.remove(key);
			}
		}
	}

}
