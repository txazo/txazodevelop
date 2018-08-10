package org.txazo.thread.thread.lang;

import org.txazo.test.base.BaseTest;

/**
 * 线程封闭，对象只能由一个线程拥有
 * 
 * <pre>
 * 1) Ad-hoc线程封闭，可忽略
 * 2) 栈封闭，使用局部变量
 * 3) ThreadLocal，每一个使用该变量的线程都有一个独立的副本
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ThreadConfinementTest extends BaseTest {

}
