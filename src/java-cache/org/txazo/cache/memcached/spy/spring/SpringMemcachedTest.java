package org.txazo.cache.memcached.spy.spring;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.SpringBaseTest;

public class SpringMemcachedTest extends SpringBaseTest {

	@Resource
	protected MemcachedClient spyMemcached;

	@Test
	public void testSpringMemcached() {
		String key = "name";
		String value = "txazo";

		spyMemcached.set(key, 3000, value);
		Assert.assertEquals(value, (String) spyMemcached.get(key));

		spyMemcached.delete(key);
		Assert.assertNull(spyMemcached.get(key));
	}

}
