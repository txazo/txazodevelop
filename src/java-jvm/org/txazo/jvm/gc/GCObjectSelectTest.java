package org.txazo.jvm.gc;

import org.txazo.test.base.BaseTest;

/**
 * 垃圾对象的判定
 * 
 * <pre>
 * 1) 引用计数算法
 * 2) 可达性分析算法(根搜索算法)
 * </pre>
 * 
 */
public class GCObjectSelectTest extends BaseTest {

	/**
	 * 引用计数算法
	 * 
	 * <pre>
	 * 1) 给对象添加一个引用计数器, 引用对象+1, 引用失效-1
	 * 2) 计数器为0的对象就是可回收的对象
	 * 3) 缺点: 很难解决对象之间相互循环引用的问题
	 * </pre>
	 */
	public void testReferenceCount() {
	}

	/**
	 * 可达性分析算法(根搜索算法)
	 * 
	 * <pre>
	 * 1) 以一系列称为GC Roots的对象为起始点, 向下搜索, 搜索的路径称为引用链
	 * 2) 一个对象到GC Roots没有任何引用链, 此对象就是可回收的
	 * 3) GC Roots
	 *    1) Java虚拟机栈(栈帧中的局部变量表)中引用的对象
	 *    2) 方法区中的类静态属性引用的对象
	 *    3) 方法区中的常量引用的对象
	 *    4） 本地方法栈中JNI(Native方法)的引用对象
	 * </pre>
	 */
	public void testRootSearch() {
	}

}
