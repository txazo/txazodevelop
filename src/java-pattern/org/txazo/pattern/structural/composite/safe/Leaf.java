package org.txazo.pattern.structural.composite.safe;

public class Leaf implements Component {

	private String name;
	private String blank = "";

	public Leaf(String name) {
		this.name = name;
	}

	@Override
	public void operation() {
		logger.info(blank + name);
	}

	@Override
	public void setBlank(String blank) {
		this.blank = blank;
	}

}
