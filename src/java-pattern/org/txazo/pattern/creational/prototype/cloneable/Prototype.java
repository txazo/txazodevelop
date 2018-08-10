package org.txazo.pattern.creational.prototype.cloneable;

public class Prototype implements Cloneable {

	private int id = 0;

	@Override
	public Prototype clone() {
		Prototype clone = null;
		try {
			clone = (Prototype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
