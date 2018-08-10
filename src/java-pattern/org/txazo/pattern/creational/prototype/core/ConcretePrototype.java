package org.txazo.pattern.creational.prototype.core;

public class ConcretePrototype implements Prototype {

	private String usernameString = null;
	private int age = 0;

	public ConcretePrototype() {
	}

	public ConcretePrototype(String usernameString, int age) {
		this.usernameString = usernameString;
		this.age = age;
	}

	@Override
	public Prototype clone() {
		ConcretePrototype prototype = new ConcretePrototype();
		prototype.setUsernameString(usernameString);
		prototype.setAge(age);
		return prototype;
	}

	public String getUsernameString() {
		return usernameString;
	}

	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
