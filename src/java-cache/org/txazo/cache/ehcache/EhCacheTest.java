package org.txazo.cache.ehcache;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class EhCacheTest extends BaseTest {

	@Test
	public void testEhCache() {
		String cacheName = "org.txazo.cache.EhCache";
		String key = "name";
		String value = "txazo";

		EhCache.put(cacheName, key, value);
		Assert.assertEquals(value, (String) EhCache.get(cacheName, key));

		EhCache.remove(cacheName, key);
		Assert.assertNull(EhCache.get(cacheName, key));

		EhCache.shutdown();
	}

}
