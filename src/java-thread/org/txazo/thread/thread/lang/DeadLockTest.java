package org.txazo.thread.thread.lang;

import org.txazo.test.base.BaseTest;

/**
 * 死锁
 * 
 * <pre>
 * 1) 死锁:
 *       Thread 1 lock A, wait for B
 *       Thread 2 lock B, wait for C
 *       Thread 3 lock C, wait for A
 * 2) 避免死锁:
 *     a. 加锁顺序
 *         Thread 1 lock A B
 *         Thread 2 wait for A B, lock B C
 *         Thread 3 wait for A B C, lock A C
 *     b. 加锁时限, 获取锁的时候有超时时间
 *     c. 死锁检测
 * </pre>
 * 
 */
public class DeadLockTest extends BaseTest {

}
