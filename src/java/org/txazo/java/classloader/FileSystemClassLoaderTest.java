package org.txazo.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * FileSystem ClassLoader
 * 
 * @author txazo
 * 
 */
public class FileSystemClassLoaderTest extends BaseTest {

	@Test
	public void testFileSystemClassLoader() throws ClassNotFoundException {
		String className = "org.txazo.blog.bean.BlogIndex";
		FileSystemClassLoader classLoader = new FileSystemClassLoader("F:/classes");
		Class<?> clazz = classLoader.loadClass(className);
		Assert.assertEquals(className, clazz.getName());
	}

	/**
	 * 自定义文件类加载器
	 * 
	 * <pre>
	 * 1) 自定义类加载器没有指定父类加载器的情况下，默认的父类加载器即为系统类加载器
	 * 2) 两个不同的类加载器加载同一个类的生成的class实例不同，前提是类没有被父类加载器加载过
	 * </pre>
	 */
	private class FileSystemClassLoader extends ClassLoader {

		private String rootDirectory;

		public FileSystemClassLoader(String rootDirectory) {
			this.rootDirectory = rootDirectory;
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
			String file = getPathByClassName(className);

			try (FileInputStream fis = new FileInputStream(file); ByteArrayOutputStream baos = new ByteArrayOutputStream(4096)) {
				int length = -1;
				byte[] buffer = new byte[4096];
				while ((length = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
				}

				return baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}

		private String getPathByClassName(String className) {
			return rootDirectory + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
		}

	}

}
