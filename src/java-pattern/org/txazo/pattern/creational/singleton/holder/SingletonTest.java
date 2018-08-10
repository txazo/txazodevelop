package org.txazo.pattern.creational.singleton.holder;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.pattern.creational.singleton.lazy.Singleton;
import org.txazo.test.base.BaseTest;

public class SingletonTest extends BaseTest {

	@Test
	public void testSingleton() throws Exception {
		Singleton instance1 = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		Assert.assertSame(instance1, instance2);
	}

}
