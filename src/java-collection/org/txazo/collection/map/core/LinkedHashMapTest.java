package org.txazo.collection.map.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * LinkedHashMap
 * 
 * @author txazo
 * 
 */
public class LinkedHashMapTest extends BaseTest {

	@Test
	public void testMapTraverse() {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		map.put(4, 4);
		map.put(1, 1);
		map.put(3, 3);
		map.put(2, 2);

		Entry<Integer, Integer> entry = null;
		for (Iterator<Entry<Integer, Integer>> i = map.entrySet().iterator(); i.hasNext();) {
			entry = i.next();
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}

		map = new HashMap<Integer, Integer>();
		map.put(4, 4);
		map.put(1, 1);
		map.put(3, 3);
		map.put(2, 2);

		for (Iterator<Entry<Integer, Integer>> i = map.entrySet().iterator(); i.hasNext();) {
			entry = i.next();
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}

}
