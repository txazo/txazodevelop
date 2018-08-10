package org.txazo.framework.junit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Junit4集成测试
 * 
 * @author txazo
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ Test1.class, Test2.class })
public class SuiteTest {
}
