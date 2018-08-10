package org.txazo.pattern.behavior.template.core;

/**
 * 模板方法模式
 * 
 * <pre>
 * 1. 固定算法骨架，不变
 * 2. 延迟子类实现，可变
 * 3. 应用:
 *    HttpServlet
 * </pre>
 * 
 * @author tuxiaozhou
 * 
 */
public abstract class AbstractTemplate {

	/** 模板方法，固定算法骨架 */
	public final void templateMethod() {
		concreteMethod();
		abstractMethod();
		hookMethod();
		createObject();
	}

	/** 公共方法，父类实现 */
	private void concreteMethod() {
		System.out.println("concreteMethod");
	}

	/** 抽象方法，延迟子类实现 */
	protected abstract void abstractMethod();

	/** 钩子方法，提供默认实现 */
	protected void hookMethod() {
		System.out.println("hookMethod");
	}

	/** 工厂方法，延迟子类实现 */
	protected abstract Object createObject();

}
