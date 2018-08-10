package org.txazo.framework.junit.core;

import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AssertTest extends BaseTest {

	@Test
	public void testAssert() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);

		Assert.assertNull(null);
		Assert.assertNotNull(new Object());

		Integer i1 = Integer.valueOf(1024);
		Integer i2 = Integer.valueOf(1024);
		Integer i3 = Integer.valueOf(2048);
		Assert.assertSame(i1, i1);
		Assert.assertNotSame(i1, i2);
		Assert.assertEquals(i1, i2);
		Assert.assertNotEquals(i1, i3);
	}

}
