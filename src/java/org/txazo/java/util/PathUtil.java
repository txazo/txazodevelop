package org.txazo.java.util;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 路径工具类
 */
public class PathUtil extends BaseTest {

	@Test
	public void testPath() {
		logger.info(getRootPath());
		logger.info(getClassPath());
		logger.info(getClassPath(PathUtil.class));
	}

	/**
	 * 获取项目根路径
	 */
	public static String getRootPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * 获取classes路径
	 */
	public static String getClassPath() {
		return PathUtil.class.getResource("/").getPath();
	}

	/**
	 * 获取类的加载路径
	 */
	public static String getClassPath(Class<?> clazz) {
		return clazz.getResource("").getPath();
	}

}
