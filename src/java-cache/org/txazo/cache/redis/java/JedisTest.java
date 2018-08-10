package org.txazo.cache.redis.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

import redis.clients.jedis.Jedis;

/**
 * Jedis
 * 
 * @author txazo
 * 
 */
public class JedisTest extends BaseTest {

	private static Jedis jedis;

	private static Logger logger = Logger.getLogger(JedisTest.class);

	@BeforeClass
	public static void before() {
		jedis = new Jedis("112.124.6.220", 8998);
		jedis.auth("txazo");
	}

	@AfterClass
	public static void after() {
		String key = null;
		Set<String> keys = jedis.keys("*");
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			key = iterator.next();
			logger.info(jedis.type(key) + "\t" + key + "\t" + jedis.ttl(key));
		}

		jedis.flushAll();
		jedis.disconnect();
	}

	@Test
	public void testKey() {
		String key = "key";
		String value = "txazo";
		jedis.set(key, value);

		Assert.assertTrue(jedis.exists(key));

		jedis.expire(key, 5);
		Assert.assertEquals(5, jedis.ttl(key).intValue());
		jedis.expireAt(key, System.currentTimeMillis() + 5);
		logger.info(jedis.ttl(key));

		jedis.persist(key);
		Assert.assertEquals(-1, jedis.ttl(key).intValue());

		jedis.rename(key, "newKey");
		Assert.assertNull(jedis.get(key));
		Assert.assertEquals("txazo", jedis.get("newKey"));

		jedis.del("newKey");
		Assert.assertNull(jedis.get("newKey"));
	}

	@Test
	public void testString() {
		String key = "string";
		jedis.set(key, "value1");
		jedis.setex("key2", 10, "value2");
		jedis.mset("key3", "value3", "key4", "value4");
		Assert.assertEquals("value1", jedis.get(key));
		Assert.assertEquals("value2", jedis.get("key2"));
		Assert.assertEquals(10, jedis.ttl("key2").intValue());
		Assert.assertEquals("value3", jedis.get("key3"));
		Assert.assertEquals("value4", jedis.get("key4"));

		jedis.setnx(key, "setnx");
		Assert.assertEquals("value1", jedis.get(key));
		jedis.del(key);
		jedis.setnx(key, "setnx");
		Assert.assertEquals("setnx", jedis.get(key));

		jedis.append(key, "append");
		Assert.assertEquals("setnxappend", jedis.get(key));
		jedis.del(key);
		jedis.append(key, "append");
		Assert.assertEquals("append", jedis.getSet(key, "getSet"));
		Assert.assertEquals("getSet", jedis.get(key));
		Assert.assertEquals(6, jedis.strlen(key).intValue());

		jedis.set(key, "5");
		jedis.incr(key);
		Assert.assertEquals("6", jedis.get(key));
		jedis.incrBy(key, 4);
		Assert.assertEquals("10", jedis.get(key));

		List<String> values = jedis.mget("key2", "key3");
		Assert.assertEquals("value2", values.get(0));
		Assert.assertEquals("value3", values.get(1));
	}

	@Test
	public void testSet() {
		String key = "txazo_set";
		jedis.del(key);
		jedis.sadd(key, "12");
		jedis.sadd(key, "23");
		Assert.assertTrue(jedis.sismember(key, "12"));
		Assert.assertFalse(jedis.sismember(key, "34"));
	}

	@Test
	public void testList() {
		String listKey = "list";
		jedis.lpush(listKey, "list1", "list2", "list3");
		jedis.rpush(listKey, "list4", "list5", "list6");

		List<String> valueList = jedis.lrange(listKey, 0, jedis.llen(listKey) - 1);
		for (Iterator<String> iterator = valueList.iterator(); iterator.hasNext();) {
			logger.info(iterator.next());
		}

		jedis.lpop("list");
		jedis.rpop("list");

		valueList = jedis.lrange(listKey, 0, jedis.llen(listKey) - 1);
		for (Iterator<String> iterator = valueList.iterator(); iterator.hasNext();) {
			logger.info(iterator.next());
		}
	}

	@Test
	public void testHash() {
		String hashKey = "hash";
		jedis.hset(hashKey, "key1", "value1");
		jedis.hset(hashKey, "key2", "value2");
		jedis.hset(hashKey, "key3", "value3");

		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("key4", "value4");
		valueMap.put("key5", "value5");
		jedis.hmset(hashKey, valueMap);

		Assert.assertEquals("value1", jedis.hget(hashKey, "key1"));
		List<String> valueList = jedis.hmget(hashKey, "key2", "key3");
		Assert.assertEquals("value2", valueList.get(0));
		Assert.assertEquals("value3", valueList.get(1));

		String key = null;
		valueMap = jedis.hgetAll(hashKey);
		for (Iterator<String> i = valueMap.keySet().iterator(); i.hasNext();) {
			key = i.next();
			logger.info(key + "\t" + valueMap.get(key));
		}

		jedis.hdel(hashKey, "key1");
		jedis.hlen(hashKey);
		jedis.hexists(hashKey, "key1");
		jedis.hkeys(hashKey);
		jedis.hvals(hashKey);
	}

}
