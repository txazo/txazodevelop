package org.txazo.thread.thread.lang;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.thread.annotation.Immutable;

/**
 * 不变性，不可变对象一定是线程安全的
 * 
 * <pre>
 * 1) 基本类型: final修饰
 * 2) 引用类型: 创建后内部状态不能被改变
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ImmutableTest extends BaseTest {

	@Test
	public void testImmutable() throws InterruptedException {
		ImmutableObject immutable = new ImmutableObject(0);
		MyRunnable runnable = new MyRunnable(immutable);

		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}

		Thread.sleep(1000);
	}

	/**
	 * 不可变对象
	 */
	@Immutable
	public final class ImmutableObject {

		private int value = 0;

		public ImmutableObject(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public ImmutableObject addValue(int value) {
			return new ImmutableObject(this.value + value);
		}

	}

	public class MyRunnable implements Runnable {

		private ImmutableObject immutable;

		private MyRunnable(ImmutableObject immutable) {
			this.immutable = immutable;
		}

		@Override
		public void run() {
			logger.info("Immutable value {}", immutable.addValue(ThreadLocalRandom.current().nextInt(5)).getValue());
		}

	}

}
