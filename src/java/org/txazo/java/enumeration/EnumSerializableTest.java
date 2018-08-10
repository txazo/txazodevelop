package org.txazo.java.enumeration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 枚举 - 序列化
 * 
 * <pre>
 * 1) 枚举序列化时只会写入枚举对象的name属性
 * 2) 枚举反序列化时读取name属性并调用Enum.valueOf(enumType, name)返回指定的枚举常量
 * </pre>
 */
public class EnumSerializableTest extends BaseTest {

	private String file = "D:/test/enum.txt";

	/**
	 * 枚举序列化
	 * 
	 * <pre>
	 * 1) 写入Enum.name()
	 * </pre>
	 */
	@Test
	public void testEnumSerializableWrite() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(Platform.PC);
		IOUtils.closeQuietly(oos);
	}

	/**
	 * 枚举反序列化
	 * 
	 * <pre>
	 * 1) 读取name
	 * 2) Enum enum = Enum.valueOf(enumType, name)
	 * </pre>
	 */
	@Test
	public void testEnumSerializableRead() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Platform platform = (Platform) ois.readObject();
		logger.info("{}", platform.platform);
		IOUtils.closeQuietly(ois);
	}

	/**
	 * Enum.valueOf实现
	 * 
	 * <pre>
	 * 1) 枚举类的Class实例对象维护一个当前枚举类常量的Map<String, Enum>, key即为Enum.name()
	 * </pre>
	 */
	@Test
	public void testEnumvalueOf() {
		Assert.assertSame(Platform.PC, Enum.valueOf(Platform.class, "PC"));
	}

}

enum Platform {

	PC("pc"), WAP("wap"), ANDROID("android"), IOS("ios");

	final String platform;

	Platform(String platform) {
		this.platform = platform;
	}

}