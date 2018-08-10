package org.txazo.pattern.behavior.memento.inside;

/**
 * 备忘录模式
 * 
 * <pre>
 * 1. 内部类实现备忘录，拒绝外部访问
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
