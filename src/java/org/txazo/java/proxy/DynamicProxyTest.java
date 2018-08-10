package org.txazo.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java动态代理 - 接口代理
 * 
 * @author txazo
 * 
 */
public class DynamicProxyTest extends BaseTest {

	@Test
	public void testDynamicProxy() {
		DynamicProxy proxy = new DynamicProxy();
		Facade facadeProxy = (Facade) proxy.getProxy(new FacadeImpl());
		facadeProxy.facade();
	}

	private class DynamicProxy implements InvocationHandler {

		private Object target;

		public Object getProxy(Object target) {
			this.target = target;
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			logger.info("DynamicProxy begin");
			result = method.invoke(target, args);
			logger.info("DynamicProxy end");
			return result;
		}
	}

	private interface Facade {

		public void facade();

	}

	private class FacadeImpl implements Facade {

		public void facade() {
			logger.info("facade");
		}

	}

}
