package org.txazo.pattern.behavior.strategy.core;

/**
 * 策略模式
 * 
 * <pre>
 * 1. 分离算法，选择实现
 * </pre>
 * 
 * @author txazo
 * 
 */
public class Context {

	private Strategy strategy;

	public int calculate(int a, int b) {
		return strategy.calculate(a, b);
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
