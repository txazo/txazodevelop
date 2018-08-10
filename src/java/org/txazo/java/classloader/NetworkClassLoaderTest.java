package org.txazo.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Network ClassLoader
 * 
 * @author txazo
 * 
 */
public class NetworkClassLoaderTest extends BaseTest {

	@Test
	public void test() throws ClassNotFoundException {
		String className = "org.txazo.java.reflect.classloader.User";
		NetworkClassLoader classLoader = new NetworkClassLoader("http://127.0.0.1/networkClassLoader");
		Class<?> clazz = classLoader.loadClass(className);
		Assert.assertEquals(className, clazz.getName());
	}

	/**
	 * 自定义网络类加载器
	 */
	private class NetworkClassLoader extends ClassLoader {

		private String rootUrl;

		public NetworkClassLoader(String rootUrl) {
			this.rootUrl = rootUrl;
		}

		/**
		 * 重载findClass方法
		 */
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException(name);
			}

			return defineClass(name, classData, 0, classData.length);
		}

		private byte[] getClassData(String className) {
			String path = getPathByClassName(className);

			InputStream is = null;
			ByteArrayOutputStream baos = null;
			try {
				URL url = new URL(path);
				is = url.openStream();
				baos = new ByteArrayOutputStream(4096);

				int length = -1;
				byte[] buffer = new byte[4096];
				while ((length = is.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
				}

				return baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(baos);
				IOUtils.closeQuietly(is);
			}

			return null;
		}

		private String getPathByClassName(String className) {
			return rootUrl + "/" + className.replace('.', '/') + ".class";
		}

	}

}
