package org.txazo.java.math.random;

import java.util.Random;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 随机数[0, max)
 * 
 * @author txazo
 * 
 */
public class RandomTest extends BaseTest {

	private static int max = 10;

	@Test
	public void testRandom1() {
		/** System.currentTimeMillis() */
		long time = System.currentTimeMillis();
		int random = (int) time % max;
		System.out.println(random);
	}

	@Test
	public void testRandom2() {
		/** Math.random() */
		double d = Math.random();
		int random = (int) (d * max);
		System.out.println(random);
	}

	@Test
	public void testRandom3() {
		/** new Random().nextInt() */
		Random r = new Random();
		int random = r.nextInt(max);
		System.out.println(random);
	}

}
