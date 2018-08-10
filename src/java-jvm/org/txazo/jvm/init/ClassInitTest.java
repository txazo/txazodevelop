package org.txazo.jvm.init;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 类初始化, 首次被使用时触发初始化
 * 
 * <pre>
 * 类初始化过程
 * 1) 初始化非final静态变量
 * 2) 执行static代码块
 * </pre>
 * 
 * <pre>
 * 1) main
 * 2) new
 * 3) 操作非final静态变量
 * 4) 调用静态方法
 * 5) 反射
 * 6) 父类
 * </pre>
 */
public class ClassInitTest extends BaseTest {

	static {
		System.out.println("ClassInitTest init");
	}

	/** main */
	public static void main(String[] args) {
		System.out.println("ClassInitTest main");
	}

	/** new */
	@Test
	public void testNew() {
		new Parent();
	}

	/** 操作非final静态变量 */
	@Test
	public void testStaticNonFinalField() {
		System.out.println(Parent.a);
	}

	/** 调用静态方法 */
	@Test
	public void testStaticMethod() {
		Parent.getA();
	}

	/** 反射 */
	@Test
	public void testReflect() throws ClassNotFoundException {
		Class.forName("org.txazo.jvm.init.Parent");
	}

	/** 父类 */
	@Test
	public void testSuperClass() throws ClassNotFoundException {
		new Child();
	}

	/** 子类引用父类的非final静态变量, 只会触发父类初始化 */
	@Test
	public void testSuperStaticNonFinalField() throws ClassNotFoundException {
		System.out.println(Child.a);
	}

	/** 操作final静态变量, 不会触发类初始化 */
	@Test
	public void testStaticFinalField() {
		System.out.println(Parent.b);
	}

	/** 数组定义引用类, 不会触发类初始化 */
	@Test
	public void testArrayReference() {
		Parent[] parents = new Parent[5];
		System.out.println(parents.length);
	}

}

class Parent {

	public static int a = 1;

	public static final int b = 1;

	static {
		System.out.println("Parent init");
	}

	public static int getA() {
		return a;
	}

}

class Child extends Parent {

	static {
		System.out.println("Child init");
	}

}