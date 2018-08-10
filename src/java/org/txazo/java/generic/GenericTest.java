package org.txazo.java.generic;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 泛型
 * 
 * <pre>
 * 1) 泛型声明: 接口, 类, 方法
 * 2) 泛型的类型参数只能是类类型，不能是简单类型或对象类型
 * </pre>
 * 
 * @author txazo
 * 
 */
public class GenericTest extends BaseTest {

	/** 泛型接口 */
	private interface GenericInterface<T> {

		public T method(T t);

	}

	/** 泛型类 */
	private class GenericClass<T> implements GenericInterface<T> {

		@Override
		public T method(T t) {
			return t;
		}

	}

	private class GenericMethod {

		/** 泛型方法 */
		public <T> T method(T t) {
			return t;
		}

	}

	@Test
	public void testGeneric() {
		GenericInterface<String> genericInterface = new GenericClass<String>();
		logger.info("{}", genericInterface.method("txazo"));

		GenericMethod genericMethod = new GenericMethod();
		logger.info("{}", genericMethod.method("txazo"));
	}

}
