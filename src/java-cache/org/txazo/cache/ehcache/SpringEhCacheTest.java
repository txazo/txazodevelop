package org.txazo.cache.ehcache;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.SpringBaseTest;

public class SpringEhCacheTest extends SpringBaseTest {

	@Resource
	private Ehcache springEhCache;

	@Test
	public void testSpringEhCache() {
		String key = "name";
		String value = "txazo";

		Element element = new Element(key, value);
		springEhCache.put(element);

		element = springEhCache.get(key);
		Assert.assertEquals(value, (String) element.getObjectValue());

		springEhCache.remove(key);

		element = springEhCache.get(key);
		Assert.assertNull(element);
	}

}
