package org.txazo.pattern.structural.composite.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 组合模式
 * 
 * <pre>
 * 1. 对象递归组合
 * 2. 整体-部分的层次结构，用户对叶子对象和组合对象的使用具有一致性
 * 3. 应用
 *    1) AWT容器(组合对象:Component Container Panel Dialog，叶子对象:Button Label List)
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Composite extends Component {

	public Composite(String name) {
		super(name);
	}

	private List<Component> components = new ArrayList<Component>();

	@Override
	public void operation() {
		logger.info(blank + name);

		Iterator<Component> iterator = components.iterator();
		while (iterator.hasNext()) {
			Component component = iterator.next();
			component.setBlank(blank + "------");
			component.operation();
		}
	}

	@Override
	public void addComponent(Component component) {
		components.add(component);
	}

	@Override
	public void removeComponent(Component component) {
		components.remove(component);
	}

}
