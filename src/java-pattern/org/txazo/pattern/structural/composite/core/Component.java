package org.txazo.pattern.structural.composite.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Component {

	protected static Logger logger = LoggerFactory.getLogger("stdout");

	protected String name;
	protected String blank = "";

	public Component(String name) {
		this.name = name;
	}

	public abstract void operation();

	public void addComponent(Component component) {
	}

	public void removeComponent(Component component) {
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}

}
