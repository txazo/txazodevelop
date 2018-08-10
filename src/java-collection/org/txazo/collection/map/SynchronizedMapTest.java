package org.txazo.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Collections.synchronizedMap
 * 
 * <pre>
 * 1) 线程安全
 * 2) 原理: 对象同步块 - synchronized (mutex)
 * </pre>
 * 
 * @author txazo
 * 
 */
public class SynchronizedMapTest extends BaseTest {

	@Test
	public void testSynchronizedMap() {
		Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<String, Object>());
		synchronizedMap.remove("key");
	}

}
