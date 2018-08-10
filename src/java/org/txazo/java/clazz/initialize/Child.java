package org.txazo.java.clazz.initialize;

/**
 * 继承类初始化
 * 
 * <pre>
 * 1) 父类静态变量和静态块
 * 2) 子类静态变量和静态块
 * 3) 父类非静态变量和非静态块
 * 4) 父类构造方法
 * 5) 子类非静态变量和非静态块
 * 6) 子类构造方法
 * </pre>
 */
public class Child extends Parent {

	public static Child c1 = new Child("c1");
	public static Child c2 = new Child("c2");
	public static String j = print("j");
	protected String i = print("i");

	static {
		System.out.println("child static block");
	}

	{
		System.out.println("child block");
	}

	public Child(String name) {
		super(name);
		System.out.println("child construct " + name);
	}

	public static String print(String name) {
		System.out.println("child print " + name);
		return null;
	}

	public static void main(String[] args) {
		new Child("main");
	}

}
