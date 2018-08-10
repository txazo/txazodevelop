package org.txazo.java.string;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 字符串
 * 
 * <pre>
 * 1) String: 不可变
 * 2) StringBuffer: 可变, 线程安全
 * 3) StringBuilder: 可变, 线程不安全
 * </pre>
 */
public class StringBufferTest extends BaseTest {

	/**
	 * StringBuffer StringBuilder
	 * 
	 * <pre>
	 * 1) 实现机制: char[]
	 * 2) 容量: 默认16, 不足时扩容一倍, 创建时可设置初始容量, 避免频繁扩容
	 * </pre>
	 */
	@Test
	public void testStringBuffer() {
		StringBuffer sb = new StringBuffer(30);
		sb.append("I").append(" love").append(" you");
		logger.info(sb.toString());

		String s1 = "I ";
		String s2 = "love ";
		String s3 = "you";
		/** 编译优化, String str = new StringBuffer(s1).append(s2).append(s3) */
		String str = s1 + s2 + s3;
		logger.info(str);
	}

}
