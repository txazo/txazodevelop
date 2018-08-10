package org.txazo.pattern.behavior.memento.core;

/**
 * 备忘录模式
 * 
 * <pre>
 * 1. 保存和恢复内部状态，备忘的目的是恢复
 * 2. 捕获的内部状态存放在备忘录对象中，备忘录对象存放在管理者对象中
 * 3. 管理者对象，提供保存和获取备忘录对象的功能
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Caretaker {

	private Memento memento;

	/** 获取备忘录 */
	public Memento getMemento() {
		return memento;
	}

	/** 保存备忘录 */
	public void saveMemento(Memento memento) {
		this.memento = memento;
	}

}
