package org.txazo.jvm.gc;

import org.txazo.test.base.BaseTest;

/**
 * 垃圾回收算法
 * 
 * <pre>
 * 1) 标记—清除算法: 先标记，再统一回收, 会产生大量内存碎片
 * 2) 复制算法
 *    1) 内存分为两块, 一次只使用一块, 存活的对象复制到另一块上
 * 3) 标记—整理算法: 先标记，回收时存活对象向一端移动, 清理另一端的内存
 * 4) 分代收集
 *    1) 新生代: 复制算法
 *       1) 内存分为一块较大的Eden空间和两块较小的Survivor空间
 *    2) 老年代: 标记—整理算法
 * </pre>
 * 
 */
public class GCAlgorithmTest extends BaseTest {
}
