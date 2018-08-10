package org.txazo.framework.junit.core;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Junit4参数化测试
 * 
 * @author txazo
 * 
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 4 }, { 3, 9 }, { 4, 16 } });
	}

	private int input;
	private int expected;

	public ParameterizedTest(int input, int expected) {
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void testParameterized() {
		Assert.assertEquals(expected, (int) Math.pow(input, 2));
	}

}
