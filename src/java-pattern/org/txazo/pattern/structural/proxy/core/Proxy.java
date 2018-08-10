package org.txazo.pattern.structural.proxy.core;

/**
 * 代理模式
 * 
 * <pre>
 * 1. 为其它对象提供一种代理以控制对这个对象的访问
 * 2. 控制对象访问
 * 3. 分类：
 *        虚拟代理：延迟创建开销很大的对象
 *        远程代理：RMI
 *        保护代理：控制对原始对象的访问
 *        缓存代理：缓存
 *        动态代理：Java动态代理和CGlib动态代理
 * 4. 应用：
 *        Hibernate的Lazy Load
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Proxy implements Sourcable {

	private Sourcable sourcable;

	public Proxy() {
		sourcable = new Source();
	}

	@Override
	public void operation() {
		before();
		sourcable.operation();
		after();
	}

	public void before() {
		System.out.println("Proxy before");
	}

	public void after() {
		System.out.println("Proxy after");
	}

}
