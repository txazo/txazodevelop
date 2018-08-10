package org.txazo.pattern.creational.prototype.deepclone;

public class PrototypeHolder implements Cloneable {

	private int id = 0;
	private Prototype prototype = null;

	@Override
	public PrototypeHolder clone() {
		PrototypeHolder clone = null;
		try {
			clone = (PrototypeHolder) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		clone.setPrototype(prototype.clone());
		return clone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prototype getPrototype() {
		return prototype;
	}

	public void setPrototype(Prototype prototype) {
		this.prototype = prototype;
	}

}
