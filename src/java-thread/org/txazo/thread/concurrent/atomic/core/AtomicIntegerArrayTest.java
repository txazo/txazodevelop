package org.txazo.thread.concurrent.atomic.core;

import java.util.concurrent.atomic.AtomicIntegerArray;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AtomicIntegerArrayTest extends BaseTest {

	@Test
	public void testAtomicIntegerArray() {
		AtomicIntegerArray array = new AtomicIntegerArray(new int[] { 1, 2, 3, 4, 5 });

		/** 返回数组索引i的值，并以原子方式将索引i的元素加1 */
		System.out.println(array.getAndIncrement(0));
		/** 原子方式将索引i的元素加1，并返回新值 */
		System.out.println(array.incrementAndGet(0));
		/** 原子方式将索引i的元素加指定值，并返回新值 */
		System.out.println(array.addAndGet(0, 2));
		/** 返回数组索引i的值，并以原子方式将索引i的元素加指定值 */
		System.out.println(array.getAndAdd(0, 5));
		/** 返回数组索引i的值，并以原子方式将索引i的值设为指定值 */
		System.out.println(array.getAndSet(0, 15));
		/** 返回数组索引i的值 */
		System.out.println(array.get(0));
		/** 原子方式将索引i的元素设为指定值 */
		array.set(0, 20);
		System.out.println(array.get(0));
	}

}
