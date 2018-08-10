package org.txazo.java.string;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符串常量
 * 
 * <pre>
 * 1) 字符串常量 > 字符串常量池引用
 * 2) 字符串常量表达式 > 字符串常量池引用
 * 3) String.intern() > 字符串常量池引用
 * 4) new String() > 堆中对象引用
 * 5) 运行时连接字符串 > 堆中对象引用
 * </pre>
 */
public class StringConstantTest extends BaseTest {

	@Test
	public void test1() {
		/** 字符串常量 */
		String s1 = "hello";
		/** 字符串常量 */
		String s2 = "hello";
		/** 字符串常量表达式, 编译优化, s3 = "hello" */
		String s3 = "he" + "llo";
		Assert.assertTrue(s1 == s2);
		Assert.assertTrue(s1 == s3);
	}

	@Test
	public void test2() {
		String s1 = "hello";
		String s2 = new String("hello");
		/** 运行时连接字符串 */
		String s3 = "he" + new String("llo");
		Assert.assertFalse(s1 == s2);
		Assert.assertFalse(s1 == s3);
	}

	/**
	 * String.intern()
	 * 
	 * <pre>
	 * 把一个运行时创建的字符串添加到字符串常量池
	 * </pre>
	 */
	@Test
	public void test3() {
		String s1 = "hello";
		String s2 = new String("hello");
		String s3 = s2.intern();
		Assert.assertTrue(s1 == s3);
	}

}
