package org.txazo.pattern.creational.singleton.lazy;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class SingletonTest extends BaseTest {

	@Test
	public void testSingleton() throws Exception {
		Singleton instance1 = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		Assert.assertSame(instance1, instance2);
	}

}
