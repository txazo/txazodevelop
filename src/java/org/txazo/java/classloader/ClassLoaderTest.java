package org.txazo.java.classloader;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 类加载器
 * 
 * <pre>
 * 1) Bootstrap ClassLoader: %JAVA_HOME%/lib, sun.boot.class.path
 * 2) Extension ClassLoader: %JAVA_HOME%/lib/ext, java.ext.dirs
 * 3) System ClassLoader: classpath, java.class.path
 * 4)
 * </pre>
 * 
 * <pre>
 * 双亲委派机制
 * 1) loadClass()
 * 2) findLoadedClass() - true - return
 * 3) findLoadedClass() - false - parentClassLoader - 1)
 * 4) findClass()
 * </pre>
 * 
 */
public class ClassLoaderTest extends BaseTest {

	@Test
	public void testClassLoader() {
		/** System ClassLoader, 系统类加载器 */
		ClassLoader systemClassLoader = Thread.currentThread().getContextClassLoader();
		Assert.assertEquals("sun.misc.Launcher$AppClassLoader", systemClassLoader.getClass().getName());

		/** Extension ClassLoader, 扩展类加载器 */
		ClassLoader extensionClassLoader = systemClassLoader.getParent();
		Assert.assertEquals("sun.misc.Launcher$ExtClassLoader", extensionClassLoader.getClass().getName());

		/** Bootstrap ClassLoader, 启动类加载器 */
		ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
		Assert.assertNull(bootstrapClassLoader);
	}

	@Test
	public void testClassLoaderDir() {
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		ClassLoader extensionClassLoader = systemClassLoader.getParent();
		showClassLoaderDir(systemClassLoader);
		showClassLoaderDir(extensionClassLoader);
	}

	private void showClassLoaderDir(ClassLoader classLoader) {
		URL[] urls = ((URLClassLoader) classLoader).getURLs();
		for (int i = 0; i < urls.length; i++) {
			logger.info("{} {}", classLoader.getClass().getName(), urls[i]);
		}
	}

}
