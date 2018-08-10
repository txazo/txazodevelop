package org.txazo.java.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.lang.String
 * 
 * <pre>
 * 1) private final char value[], 不可变
 * </pre>
 */
public class StringTest extends BaseTest {

	/**
	 * VM Args: -Xms10m -Xmx10m -verbose:gc
	 */
	@Test
	public void testSubstring() {
		/** 1M内存 */
		String str = new String(new char[1024 * 1024 * 1024]);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			/**
			 * <pre>
			 * String.substring()
			 * 1) 采用空间换时间的策略, 复制原始String对象的char[]来创建新的String对象
			 * 2) 当原始String对象的长度较大，容易造成内存泄漏
			 * 3) 解决方案: new String(str.substring())
			 * </pre>
			 */
			list.add(str.substring(0, 5));
		}
	}

}
