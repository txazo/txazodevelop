package org.txazo.jvm.parameter;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * JVM启动参数 -verbose
 * 
 * VM Args: -verbose[:class|gc|jni]
 */
public class JvmVerboseTest extends BaseTest {

	/**
	 * VM Args: -verbose:class
	 */
	@Test
	public void testVerboseClass() {
	}

	/**
	 * VM Args: -verbose:gc
	 */
	@Test
	public void testVerboseGC() {
		System.gc();
	}

}
