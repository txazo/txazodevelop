package org.txazo.java.lang;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 自动装箱和拆箱
 * 
 * <pre>
 * 装箱: 基本类型转换为封装类型
 * 拆箱: 封装类型转换为基本类型
 * </pre>
 */
public class AutoBoxTest extends BaseTest {

	@Test
	public void test1() {
		/** 装箱, a = Integer.valueOf(10) */
		Integer a = 10;
		/** 拆箱, b = a.intValue() */
		int b = a;
		/** 拆箱 */
		Assert.assertTrue(a == b);
		/** 装箱 */
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void test2() {
		/** 装箱, a = Integer.IntegerCache.cache[10 + 128] */
		Integer a = 10;
		/** 装箱, b = Integer.IntegerCache.cache[10 + 128] */
		Integer b = 10;
		/** c = new Integer(10) */
		Integer c = new Integer(10);
		int d = 10;

		Assert.assertTrue(a == b);
		Assert.assertFalse(a == c);
		/** 拆箱 */
		Assert.assertTrue(a == d);
		/** 拆箱 */
		Assert.assertTrue(c == d);
	}

	/**
	 * <pre>
	 * Integer.valueOf(i)
	 * -128 <= i <= 127, return Integer.IntegerCache.cache[i + 128]
	 * i < -128 || i > 127, return new Integer(i)
	 * </pre>
	 */
	@Test
	public void test3() {
		/** 装箱, a = new Integer(1000) */
		Integer a = 1000;
		/** 装箱, b = new Integer(1000) */
		Integer b = 1000;
		/** c = new Integer(1000) */
		Integer c = new Integer(1000);
		int d = 1000;

		Assert.assertFalse(a == b);
		Assert.assertFalse(a == c);
		/** 拆箱 */
		Assert.assertTrue(a == d);
		/** 拆箱 */
		Assert.assertTrue(c == d);
	}

	/**
	 * 拆箱操作时，保证封装对象不为null
	 */
	@SuppressWarnings({ "unused", "null" })
	@Test(expected = NullPointerException.class)
	public void test4() {
		Integer a = null;
		/** 拆箱, b = a.intValue() */
		int b = a;
	}

	/**
	 * 拆箱操作时，保证封装对象不为null
	 */
	@SuppressWarnings({ "unused", "null" })
	@Test(expected = NullPointerException.class)
	public void test5() {
		Integer a = null;
		int b = 10;
		/** 拆箱 */
		boolean equal = a == b;
	}

}
