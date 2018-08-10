package org.txazo.collection.map.traverse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Map遍历
 * 
 * @author txazo
 * 
 */
public class MapTraverseTest extends BaseTest {

	@Test
	public void testMapTraverse() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 5; i++) {
			map.put(i, i);
		}

		// Map遍历方式一
		for (Iterator<Map.Entry<Integer, Integer>> i = map.entrySet().iterator(); i.hasNext();) {
			Map.Entry<Integer, Integer> entry = i.next();
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}

		// Map遍历方式二
		Integer key = null;
		for (Iterator<Integer> i = map.keySet().iterator(); i.hasNext();) {
			key = i.next();
			System.out.println(key + "\t" + map.get(key));
		}

		// Map遍历方式三
		for (Iterator<Integer> i = map.values().iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}

}
