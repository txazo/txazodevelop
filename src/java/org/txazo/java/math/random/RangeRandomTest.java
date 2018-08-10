package org.txazo.java.math.random;

import java.util.Random;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 区间随机数[min, max]
 * 
 * @author txazo
 * 
 */
public class RangeRandomTest extends BaseTest {

	private static int min = 10;
	private static int max = 20;

	@Test
	public void testRangeRandom1() {
		int r = min + (int) (System.currentTimeMillis() % (max - min + 1));
		System.out.println(r);
	}

	@Test
	public void testRangeRandom2() {
		long r = Math.round(min + Math.random() * (max - min));
		System.out.println(r);
	}

	@Test
	public void testRangeRandom3() {
		Random random = new Random();
		int r = min + random.nextInt(max - min + 1);
		System.out.println(r);
	}

}
