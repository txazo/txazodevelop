package org.txazo.pattern.behavior.memento.core;

public class Originator {

	private String state;

	/** 返回一个新的备忘录对象 */
	public Memento createMemento() {
		return new Memento(state);
	}

	/** 恢复备忘录对象 */
	public void restoreMemento(Memento memento) {
		state = memento.getState();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
