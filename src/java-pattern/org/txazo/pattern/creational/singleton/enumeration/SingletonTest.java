package org.txazo.pattern.creational.singleton.enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class SingletonTest extends BaseTest {

	@Test
	public void testSingleton() throws Exception {
		Singleton instance1 = Singleton.INSTANCE;
		Singleton instance2 = Singleton.INSTANCE;
		Assert.assertSame(instance1, instance2);
	}

}
