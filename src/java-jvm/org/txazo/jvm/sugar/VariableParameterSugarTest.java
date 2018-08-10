package org.txazo.jvm.sugar;

import org.txazo.test.base.BaseTest;

/**
 * 可变长参数 - 语法糖
 * 
 * <pre>
 * 1) 在编译阶段，可变长参数被编译为数组
 * 2) 可变长参数必须是方法参数的最后一项
 * 3) 可变长参数和数组是同一种方法参数类型
 * </pre>
 */
public class VariableParameterSugarTest extends BaseTest {

	/**
	 * 可变长参数
	 */
	public void method(String... args) {
	}

	/**
	 * 可变长参数 - 反编译
	 */
	public void methodDeCompile(String args[]) {
	}

}
