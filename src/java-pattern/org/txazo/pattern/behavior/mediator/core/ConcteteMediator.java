package org.txazo.pattern.behavior.mediator.core;

/**
 * 中介者模式
 * 
 * <pre>
 * 1. 封装一系列的对象交互，对象之间不需要显示引用
 * 2. 松散耦合，集中控制交互
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ConcteteMediator implements Mediator {

	private ConcreteColleagueA colleagueA = null;
	private ConcreteColleagueB colleagueB = null;

	@Override
	public void change(Colleague colleague) {
		if (colleagueA == colleague) {
			colleagueB.operation();
		} else if (colleagueB == colleague) {
			colleagueA.operation();
		}
	}

	public void setColleagueA(ConcreteColleagueA colleagueA) {
		this.colleagueA = colleagueA;
	}

	public void setColleagueB(ConcreteColleagueB colleagueB) {
		this.colleagueB = colleagueB;
	}

}
