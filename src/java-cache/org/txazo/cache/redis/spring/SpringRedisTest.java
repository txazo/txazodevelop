package org.txazo.cache.redis.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.txazo.test.base.SpringBaseTest;

/**
 * Spring Redis
 * 
 * @author txazo
 * 
 */
public class SpringRedisTest extends SpringBaseTest {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valueOperations;

	@Resource(name = "stringRedisTemplate")
	private ListOperations<String, String> listOperations;

	@Resource(name = "stringRedisTemplate")
	private SetOperations<String, String> setOperations;

	@Resource(name = "stringRedisTemplate")
	private ZSetOperations<String, String> zSetOperations;

	@Resource(name = "stringRedisTemplate")
	private HashOperations<String, String, String> hashOperations;

	@Test
	public void testRedisTemplate() {
		redisTemplate.opsForValue();
		redisTemplate.opsForList();
		redisTemplate.opsForSet();
		redisTemplate.opsForZSet();
		redisTemplate.opsForHash();
	}

	@Test
	public void testString() {
		String key = "string";
		valueOperations.set(key, "set");
		Assert.assertEquals("set", valueOperations.getAndSet(key, "getAndSet"));
		Assert.assertEquals("getAndSet", valueOperations.get(key));
		valueOperations.append(key, "append");
		Assert.assertEquals("getAndSetappend", valueOperations.get(key));
		valueOperations.set(key, "10");
		valueOperations.increment(key, 5);
		Assert.assertEquals("15", valueOperations.get(key));

		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("key1", "value1");
		valueMap.put("key2", "value2");
		valueOperations.multiSet(valueMap);
		List<String> keyList = new ArrayList<String>();
		keyList.add("key1");
		keyList.add("key2");
		List<String> valueList = valueOperations.multiGet(keyList);
		Assert.assertEquals("value1", valueList.get(0));
		Assert.assertEquals("value2", valueList.get(1));
	}

	@Test
	public void testList() {
		stringRedisTemplate.delete("list");
		listOperations.leftPushAll("list", new String[] { "1", "2", "3" });
		Assert.assertEquals("3", listOperations.leftPop("list"));
		Assert.assertEquals("1", listOperations.rightPop("list"));
	}

}
