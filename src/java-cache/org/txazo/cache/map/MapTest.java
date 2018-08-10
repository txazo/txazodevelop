package org.txazo.cache.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Map
 * 
 * @author txazo
 * 
 */
public class MapTest extends BaseTest {

	@Test
	public void testMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "txazo");
		map.get("name");
		map.remove("name");
		map.containsKey("name");
	}

}
