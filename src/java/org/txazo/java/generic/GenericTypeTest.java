package org.txazo.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * 获取泛型类型 - 实例化抽象类
 * 
 * @author txazo
 * 
 */
public class GenericTypeTest extends BaseTest {

	private abstract class GenericType<T> {

		public Class<?> getGenericType(int index) {
			Type type = getClass().getGenericSuperclass();
			if (!(type instanceof ParameterizedType)) {
				return Object.class;
			}
			Type[] params = ((ParameterizedType) type).getActualTypeArguments();
			if (params == null || params.length < 1) {
				throw new RuntimeException("index outof bounds");
			}
			if (!(params[index] instanceof Class)) {
				return Object.class;
			}
			return (Class<?>) params[index];
		}

		@SuppressWarnings("unchecked")
		public T getGenericParameterInstance() throws Exception {
			return (T) getGenericType(0).newInstance();
		}

	}

	@Test
	public void testGenericType() throws Exception {
		GenericType<String> genericType = new GenericType<String>() {
		};
		logger.info("{}", genericType.getGenericType(0));
		Object obj = genericType.getGenericParameterInstance();
		logger.info("{}", obj.getClass());
	}

}
