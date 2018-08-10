package org.txazo.pattern.structural.composite.extend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 组合模式
 * 
 * <pre>
 * 1. 支持消除环形引用
 * 2. 支持删除中间节点
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Composite extends Component {

	private List<Component> components = new ArrayList<Component>();

	public Composite(String name) {
		super(name);
	}

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
	public void addChild(Component child) {
		if (!isSelf(child)) {
			child.setParent(this);
			components.add(child);
		} else {
			logger.info("This Object Exist In Parent");
		}
	}

	@Override
	public void removeChild(Component child) {
		int index = components.indexOf(child);
		if (index != -1) {
			for (Component component : child.getChildren()) {
				component.setParent(this);
				components.add(component);
			}
			components.remove(child);
		}
	}

	@Override
	public Component getChild(int index) {
		if (index >= 0 && index < components.size()) {
			return components.get(index);
		}
		return null;
	}

	@Override
	public List<Component> getChildren() {
		return components;
	}

	public boolean isSelf(Component component) {
		if (this == component) {
			return true;
		} else {
			Component parent = component.getParent();
			if (parent != null) {
				return isSelf(parent);
			}
		}
		return false;
	}

}
