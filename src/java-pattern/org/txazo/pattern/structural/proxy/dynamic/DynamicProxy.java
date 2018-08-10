package org.txazo.pattern.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式 - 动态代理(Java)
 * 
 * @author txazo
 * 
 */
public class DynamicProxy implements InvocationHandler {

	private Object object = null;

	/** 绑定代理对象 */
	public Object bind(Object object) {
		this.object = object;
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		beforeProxy();
		result = method.invoke(object, args);
		afterProxy();
		return result;
	}

	public void beforeProxy() {
		System.out.println("DynamicProxy beforeProxy");
	}

	public void afterProxy() {
		System.out.println("DynamicProxy afterProxy");
	}

}
