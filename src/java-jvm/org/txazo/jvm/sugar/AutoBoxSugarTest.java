package org.txazo.jvm.sugar;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 自动拆箱/装箱 - 语法糖
 * 
 */
public class AutoBoxSugarTest extends BaseTest {

	/**
	 * 自动拆箱/装箱
	 */
	@Test
	public void testAutoBox() {
		Integer a = 10;
		int b = a;
		logger.info("{}", b);
	}

	/**
	 * 自动拆箱/装箱 - 反编译
	 */
	@Test
	public void testAutoBoxDeCompile() {
		Integer a = Integer.valueOf(10);
		int b = a.intValue();
		logger.info("{}", Integer.valueOf(b));
	}

}
