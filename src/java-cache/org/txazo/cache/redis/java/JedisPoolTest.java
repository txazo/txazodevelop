package org.txazo.cache.redis.java;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisPool
 * 
 * @author txazo
 * 
 */
public class JedisPoolTest extends BaseTest {

	@Test
	public void testJedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1024);
		poolConfig.setMaxIdle(200);
		poolConfig.setMinIdle(20);
		poolConfig.setTestOnBorrow(true);

		JedisPool jedisPool = new JedisPool(poolConfig, "112.124.6.220", 8998);
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			jedis.set("name", "txazo");
			Assert.assertEquals("txazo", jedis.get("name").toString());
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

}
