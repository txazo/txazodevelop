package org.txazo.java.clazz;

import org.txazo.test.base.BaseTest;

/**
 * 内部类
 * 
 * @author txazo
 * 
 */
public class InnerClassTest extends BaseTest {

	private interface InnerClass {
	}

	public InnerClass getPartInnerClass() {
		/** 局部内部类 */
		class PartInnerClass implements InnerClass {
		}

		return new PartInnerClass();
	}

	public InnerClass getAnonymousInnerClass() {
		/** 匿名内部类 */
		return new InnerClass() {
		};
	}

	/**
	 * 成员内部类
	 * 
	 * <pre>
	 * 1) 可访问外部类的所有变量和方法
	 * 2) 不能定义static变量和static方法
	 * </pre>
	 */
	public class MemberInnerClass implements InnerClass {
	}

	/**
	 * 静态内部类
	 * 
	 * <pre>
	 * 1) 不可访问外部类的实例变量和实例方法
	 * </pre>
	 */
	public static class StaticInnerClass implements InnerClass {
	}

}
