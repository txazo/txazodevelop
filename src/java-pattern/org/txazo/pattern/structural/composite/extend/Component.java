package org.txazo.pattern.structural.composite.extend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Component {

	protected static Logger logger = LoggerFactory.getLogger("stdout");

	protected String name;
	protected String blank = "";

	private Component parent = null;

	public Component(String name) {
		this.name = name;
	}

	public abstract void operation();

	public void addChild(Component child) {
		throw new UnsupportedOperationException("Not Support Operation");
	}

	public void removeChild(Component child) {
		throw new UnsupportedOperationException("Not Support Operation");
	}

	public Component getChild(int index) {
		throw new UnsupportedOperationException("Not Support Operation");
	}

	public List<Component> getChildren() {
		throw new UnsupportedOperationException("Not Support Operation");
	}

	public boolean isSelf(Component component) {
		throw new UnsupportedOperationException("Not Support Operation");
	}

	public Component getParent() {
		return parent;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}

}
