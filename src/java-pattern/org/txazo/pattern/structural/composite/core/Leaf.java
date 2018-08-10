package org.txazo.pattern.structural.composite.core;

public class Leaf extends Component {

	public Leaf(String name) {
		super(name);
	}

	@Override
	public void operation() {
		logger.info(blank + name);
	}

}
