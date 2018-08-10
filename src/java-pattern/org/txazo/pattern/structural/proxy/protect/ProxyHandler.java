package org.txazo.pattern.structural.proxy.protect;

import java.util.HashSet;
import java.util.Set;

/**
 * 代理模式 - 保护代理
 * 
 * <pre>
 * 1. 控制对原始对象的访问
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ProxyHandler implements Handler {

	private static Set<String> users = new HashSet<String>();

	private Handler handler;

	static {
		users.add("root");
		users.add("admin");
		users.add("manager");
	}

	public ProxyHandler() {
		handler = new RealHandler();
	}

	@Override
	public void handle(String user) {
		if (users.contains(user)) {
			handler.handle(user);
		} else {
			System.out.println(user + " has no privilege");
		}
	}

}
