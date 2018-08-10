package org.txazo.pattern.behavior.state.core;

/**
 * 状态模式
 * 
 * <pre>
 * 1. 根据状态来分离和选择行为
 * 2. 状态决定行为，可在运行时根据状态改变行为
 * 3. 行为之间是平行的
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Context {

	private State state;

	public void request(String parameter) {
		state.handle(parameter);
	}

	public void setState(State state) {
		this.state = state;
	}

}
