package org.txazo.java.clazz.initialize;

/**
 * 类初始化
 * 
 * <pre>
 * 1) 静态变量 = 静态块(顺序)
 * 2) 非静态变量 = 非静态块(顺序)
 * 3) 构造方法
 * </pre>
 */
public class Parent {

	public static Parent p1 = new Parent("p1");
	public static Parent p2 = new Parent("p2");
	public static String j = print("j");
	protected String i = print("i");

	static {
		System.out.println("static block");
	}

	{
		System.out.println("block");
	}

	public Parent(String name) {
		System.out.println("construct " + name);
	}

	public static String print(String name) {
		System.out.println("print " + name);
		return null;
	}

	public static void main(String[] args) {
		new Parent("main");
	}

}
