package org.txazo.pattern.structural.composite.safe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Composite implements Component {

	private String name;
	private String blank = "";

	private List<Component> components = new ArrayList<Component>();

	public Composite(String name) {
		this.name = name;
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

	public void addComponent(Component component) {
		components.add(component);
	}

	public void removeComponent(Component component) {
		components.remove(component);
	}

	@Override
	public void setBlank(String blank) {
		this.blank = blank;
	}

}
