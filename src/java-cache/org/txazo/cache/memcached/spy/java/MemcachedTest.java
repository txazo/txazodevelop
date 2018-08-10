package org.txazo.cache.memcached.spy.java;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Map;

import net.spy.memcached.ConnectionObserver;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class MemcachedTest extends BaseTest {

	private static SpyMemcachedManager memcachedManager;

	private String key = "key";
	private String numericKey = "numericKey";

	@BeforeClass
	public static void before() {
		memcachedManager = SpyMemcachedManager.getInstance();
	}

	@AfterClass
	public static void after() {
		memcachedManager.shutdown();
	}

	@Test
	public void testObserver() throws IOException {
		memcachedManager.addObserver(new ConnectionObserver() {

			@Override
			public void connectionLost(SocketAddress address) {
				logger.info("connectionLost " + address.toString());
			}

			@Override
			public void connectionEstablished(SocketAddress address, int repeatConnectCount) {
				logger.info("connectionEstablished " + address.toString());
			}

		});
	}

	@Test
	public void testGet() {
		memcachedManager.set(key, "get", 5);
		Assert.assertEquals("get", memcachedManager.get(key));
	}

	@Test
	public void testAsyncGet() {
		memcachedManager.set(key, "asyncGet", 5);
		Assert.assertEquals("asyncGet", memcachedManager.asyncGet(key));
	}

	@Test
	public void testSet() {
		memcachedManager.set(key, "set", 5);
		Assert.assertEquals("set", memcachedManager.get(key));
	}

	@Test
	public void testAdd() {
		memcachedManager.delete(key);
		memcachedManager.add(key, "add1", 5);
		Assert.assertEquals("add1", memcachedManager.get(key));
		memcachedManager.add(key, "add2", 5);
		Assert.assertEquals("add1", memcachedManager.get(key));
	}

	@Test
	public void testReplace() {
		memcachedManager.delete(key);
		memcachedManager.replace(key, "replace", 5);
		Assert.assertNull(memcachedManager.get(key));
		memcachedManager.add(key, "add", 5);
		memcachedManager.replace(key, "replace", 5);
		Assert.assertEquals("replace", memcachedManager.get(key));
	}

	@Test
	public void testDelete() {
		memcachedManager.set(key, "delete", 5);
		memcachedManager.delete(key);
		Assert.assertNull(memcachedManager.get(key));
	}

	@Test
	public void testGetMulti() {
		memcachedManager.set("key1", "value1", 5);
		memcachedManager.set("key2", "value2", 5);
		Map<String, Object> valueMap = memcachedManager.getMulti(new String[] { "key1", "key2" });
		Assert.assertEquals("value1", valueMap.get("key1"));
		Assert.assertEquals("value2", valueMap.get("key2"));
	}

	@Test
	public void testAsyncGetMulti() {
		memcachedManager.set("key1", "value1", 5);
		memcachedManager.set("key2", "value2", 5);
		Map<String, Object> valueMap = memcachedManager.asyncGetMulti(new String[] { "key1", "key2" });
		Assert.assertEquals("value1", valueMap.get("key1"));
		Assert.assertEquals("value2", valueMap.get("key2"));
	}

	@Test
	public void testIncrAndDecr() {
		memcachedManager.delete(numericKey);
		Assert.assertEquals(100, memcachedManager.incr(numericKey, 5, 100, 5));
		Assert.assertEquals(105, memcachedManager.incr(numericKey, 5, 100, 5));
		Assert.assertEquals(100, memcachedManager.decr(numericKey, 5, 100, 5));
	}

	@Test
	public void testAsyncIncrAndDecr() {
		memcachedManager.delete(numericKey);
		memcachedManager.incr(numericKey, 0, 100, 5);
		Assert.assertEquals(105, memcachedManager.asyncIncr(numericKey, 5));
		Assert.assertEquals(100, memcachedManager.asyncDecr(numericKey, 5));
	}

}
