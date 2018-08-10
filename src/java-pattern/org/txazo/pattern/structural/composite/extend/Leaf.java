package org.txazo.pattern.structural.composite.extend;

public class Leaf extends Component {

	public Leaf(String name) {
		super(name);
	}

	@Override
	public void operation() {
		logger.info(blank + name);
	}

}