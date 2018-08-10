package org.txazo.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java反射
 * 
 * <pre>
 * 1) 什么是Java反射：运行时，可以获取类的属性和方法，创建类的对象，调用对象的方法，修改对象的属性
 * 2) Java反射的应用：Spring IOC，JDK动态代理，RPC远程调用，ORM映射
 * </pre>
 */
public class ReflectTest extends BaseTest {

	/**
	 * Class
	 */
	@Test
	public void testClass() throws ClassNotFoundException {
		Class<?> clazz1 = User.class;
		Class<?> clazz2 = new User().getClass();
		Class<?> clazz3 = Class.forName("org.txazo.java.reflect.User");
		Class<?> clazz4 = Thread.currentThread().getContextClassLoader().loadClass("org.txazo.java.reflect.User");

		Assert.assertSame(clazz1, clazz2);
		Assert.assertSame(clazz1, clazz3);
		Assert.assertSame(clazz1, clazz4);
	}

	/**
	 * Class.newInstance()
	 */
	@Test
	public void testClassNewInstance() throws Exception {
		Class<?> clazz = Class.forName("org.txazo.java.reflect.User");
		User user = (User) clazz.newInstance();
		Assert.assertEquals("org.txazo.java.reflect.User", user.getClass().getName());
	}

	/**
	 * 构造方法
	 */
	@Test
	public void testConstructor() throws Exception {
		Class<?> clazz = User.class;

		/** 无参构造方法 */
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		User user1 = (User) constructor.newInstance();
		user1.setUsername("root");
		user1.setPassword("root");

		/** 有参构造方法 */
		constructor = clazz.getDeclaredConstructor(new Class[] { String.class, String.class });
		User user2 = (User) constructor.newInstance("root", "root");

		Assert.assertEquals(user1, user2);
	}

	/**
	 * 方法
	 */
	@Test
	public void testMethod() throws Exception {
		Class<?> clazz = User.class;

		Constructor<?> constructor = clazz.getDeclaredConstructor();
		User user = (User) constructor.newInstance();

		Method setUsernameMethod = clazz.getDeclaredMethod("setUsername", new Class[] { String.class });
		setUsernameMethod.invoke(user, "root");
		Method setPasswordMethod = clazz.getDeclaredMethod("setPassword", new Class[] { String.class });
		setPasswordMethod.invoke(user, "root");

		Assert.assertEquals(new User("root", "root"), user);
	}

	/**
	 * 属性
	 */
	@Test
	public void testField() throws Exception {
		Class<?> clazz = User.class;

		Constructor<?> constructor = clazz.getDeclaredConstructor();
		User user = (User) constructor.newInstance();

		Field usernameField = clazz.getDeclaredField("username");
		usernameField.setAccessible(true);
		usernameField.set(user, "root");
		Field passwordField = clazz.getDeclaredField("password");
		passwordField.setAccessible(true);
		passwordField.set(user, "root");

		Assert.assertEquals(new User("root", "root"), user);
	}

}
