package org.txazo.pattern.structural.decorator.core;

/**
 * 装饰器模式
 * 
 * <pre>
 * 1. 动态为对象添加功能
 * 2. 对扩展开放，对修改关闭
 * 3. 对象组合
 * 4. 对象递归调用
 * 5. 类似AOP，可以进行权限控制和日志记录
 * 6. 应用:
 *    Java IO
 * </pre>
 * 
 * @author txazo
 * 
 */
public abstract class Decorator implements Component {

	private Component component;

	public Decorator(Component component) {
		this.component = component;
	}

	@Override
	public void operation() {
		component.operation();
	}

}
