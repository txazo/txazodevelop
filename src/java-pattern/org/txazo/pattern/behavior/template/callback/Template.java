package org.txazo.pattern.behavior.template.callback;

/**
 * Java回调实现的模板
 * 
 * <pre>
 * 1. 回调接口
 * 2. 应用:
 *    Collections.sort(List<T> list, Comparator<? super T> c)
 * </pre>
 * 
 * @author tuxiaozhou
 * 
 */
public class Template {

	/** 模板方法，固定算法骨架 */
	public void templateMethod(TemplateCallback callback) {
		concreteMethod();
		callback.abstractMethod();
	}

	private void concreteMethod() {
		System.out.println("concreteMethod");
	}

}
