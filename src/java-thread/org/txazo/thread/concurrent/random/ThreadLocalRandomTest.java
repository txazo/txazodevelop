package org.txazo.thread.concurrent.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.concurrent.ThreadLocalRandom
 * 
 * @author txazo
 * 
 */
public class ThreadLocalRandomTest extends BaseTest {

	@Test
	public void testThreadLocalRandom() {
		/** ThreadLocalRandom内部使用ThreadLocal实现 */
		Random random = ThreadLocalRandom.current();

		for (int i = 0; i < 10; i++) {
			logger.info("random {}", random.nextInt(10));
		}
	}

}
