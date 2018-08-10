package org.txazo.jvm.gc;

/**
 * JVM垃圾回收
 * 
 * <pre>
 * 1) 回收目标: Java堆中对象
 * 2) 对象优先在Eden分配
 * 3) 大对象直接进入老年代
 * 4) 长期存活的对象将进入老年代
 * 5) 新生代GC(Minor GC): Minor GC非常频繁，一般回收速度也比较快
 * 5) 老年代GC(Full GC): 出现了Full GC，经常会伴随至少一次Minor GC，一般都是等待老年代满了后才进行Full GC
 * 6) 恰当的变量作用域来控制变量回收时间才是最优雅的解决办法
 * 7) 内存:
 *    1) Java堆
 *    2) 永久代
 *    3) 直接内存
 *    4) 线程堆栈
 *    5) Socket缓冲区
 *    6) JNI代码
 *    7) 虚拟机和GC
 *    
 * </pre>
 * 
 */
public class JvmGCTest {
}
