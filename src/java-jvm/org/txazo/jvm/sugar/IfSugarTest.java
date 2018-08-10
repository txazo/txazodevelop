package org.txazo.jvm.sugar;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 条件编译 - 语法糖
 * 
 * <pre>
 * 1) 编译器对代码进行优化，对于条件永远为false的语句，Java编译器将不会对其生成字节码
 * </pre>
 */
public class IfSugarTest extends BaseTest {

	public static final boolean DEBUG = false;

	/**
	 * 条件编译
	 */
	@Test
	public void testIf() {
		if (DEBUG) {
			logger.info("debug");
		}

		final boolean flag = true;
		if (flag) {
			logger.info("true");
		} else {
			logger.info("false");
		}
	}

	/**
	 * 条件编译 - 反编译
	 */
	@Test
	@SuppressWarnings("unused")
	public void testIfDeCompile() {
		boolean flag = true;
		logger.info("true");
	}

}
