package org.txazo.pattern.behavior.memento.offline;

public class Originator {

	private String state;

	/** 返回一个新的备忘录对象 */
	public Memento createMemento() {
		return new MementoImpl(state);
	}

	/** 恢复备忘录对象 */
	public void restoreMemento(Memento memento) {
		MementoImpl mementoImpl = (MementoImpl) memento;
		state = mementoImpl.getState();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private static class MementoImpl implements Memento {

		private static final long serialVersionUID = 2520349941562974796L;

		private String state;

		public MementoImpl(String state) {
			this.state = state;
		}

		public String getState() {
			return state;
		}

	}

}
