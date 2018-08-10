package org.txazo.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

import org.txazo.test.base.BaseTest;

public class DynamicProxyImplTest extends BaseTest {

	public static void main(String[] args) {
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		System.out.println(Proxy.getProxyClass(Txazo.class.getClassLoader(), Txazo.class));

		Txazo txazo = new TxazoImpl();
		TxazoProxy ih = new TxazoProxy(txazo);
		Txazo proxy = (Txazo) Proxy.newProxyInstance(txazo.getClass().getClassLoader(), txazo.getClass().getInterfaces(), ih);

		proxy.txazo();
	}

}

interface Txazo {

	public void txazo();

	public int txazo(String str);

}

class TxazoImpl implements Txazo {

	@Override
	public void txazo() {
		System.out.println("txazo");
	}

	@Override
	public int txazo(String str) {
		System.out.println("txazo " + str);
		return 0;
	}

}

class TxazoProxy implements InvocationHandler {

	private Object target;

	public TxazoProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy before...");
		Object result = method.invoke(target, args);
		System.out.println("proxy after...");
		return result;
	}

}

/**
 * JDK动态代理类 - 反编译
 */
final class $Proxy0 extends Proxy implements Txazo {

	private static final long serialVersionUID = 977787021771967618L;

	public $Proxy0(InvocationHandler invocationhandler) {
		super(invocationhandler);
	}

	public final void txazo() {
		try {
			super.h.invoke(this, m3, null);
			return;
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
	}

	public final int txazo(String s) {
		try {
			return ((Integer) super.h.invoke(this, m4, new Object[] { s })).intValue();
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return 0;
	}

	public final boolean equals(Object obj) {
		try {
			return ((Boolean) super.h.invoke(this, m1, new Object[] { obj })).booleanValue();
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return false;
	}

	public final int hashCode() {
		try {
			return ((Integer) super.h.invoke(this, m0, null)).intValue();
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return 0;
	}

	public final String toString() {
		try {
			return (String) super.h.invoke(this, m2, null);
		} catch (Error _ex) {
		} catch (Throwable throwable) {
			throw new UndeclaredThrowableException(throwable);
		}
		return null;
	}

	private static Method m0;
	private static Method m1;
	private static Method m2;
	private static Method m3;
	private static Method m4;

	static {
		try {
			m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
			m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
			m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
			m3 = Class.forName("org.txazo.java.proxy.Txazo").getMethod("txazo", new Class[0]);
			m4 = Class.forName("org.txazo.java.proxy.Txazo").getMethod("txazo", new Class[] { Class.forName("java.lang.String") });
		} catch (NoSuchMethodException nosuchmethodexception) {
			throw new NoSuchMethodError(nosuchmethodexception.getMessage());
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}

}