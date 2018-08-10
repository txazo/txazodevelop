package org.txazo.pattern.creational.prototype.deepclone;

public class Prototype implements Cloneable {

	private int id = 0;
	private String name = null;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
