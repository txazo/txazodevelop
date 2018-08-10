package org.txazo.framework.cglib;

import java.lang.reflect.Method;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Cglib动态代理
 * 
 * @author tuxiaozhou
 *
 */
public class CglibTest extends BaseTest {

	@Test
	public void testCglib() {
		CglibProxy cglibProxy = new CglibProxy();
		Facade facade = (Facade) cglibProxy.getProxy(new Facade());
		facade.facade();
	}

	private class CglibProxy implements MethodInterceptor {

		private Object target;

		public Object getProxy(Object target) {
			this.target = target;
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(target.getClass());
			enhancer.setClassLoader(target.getClass().getClassLoader());
			enhancer.setCallback(this);
			return enhancer.create();
		}

		@Override
		public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			Object result = null;
			logger.info("CglibProxy Begin");
			result = methodProxy.invokeSuper(target, args);
			logger.info("CglibProxy End");
			return result;
		}

	}

	private class Facade {

		private Facade() {
		}

		public void facade() {
			logger.info("facade");
		}

	}

}
