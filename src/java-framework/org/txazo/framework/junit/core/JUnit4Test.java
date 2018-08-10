package org.txazo.framework.junit.core;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * JUnit4
 * 
 * 单元测试用例: @BeforeClass -> @Before -> @Test -> @After -> @AfterClass <br />
 * 单个测试方法: @Before -> @Test -> @After
 * 
 */
public class JUnit4Test extends BaseTest {

	@BeforeClass
	public static void beforeClass() {
		logger.info("before class");
	}

	@AfterClass
	public static void afterClass() {
		logger.info("after class");
	}

	@Before
	public void before() {
		logger.info("before method");
	}

	@After
	public void after() {
		logger.info("after method");
	}

	@Test
	public void testJUnit4() throws IOException {
		logger.info("testJUnit4");
	}

}