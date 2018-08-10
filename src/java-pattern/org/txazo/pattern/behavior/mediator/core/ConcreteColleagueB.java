package org.txazo.pattern.behavior.mediator.core;

public class ConcreteColleagueB extends Colleague {

	public ConcreteColleagueB(Mediator mediator) {
		super(mediator);
	}

	public void operationB() {
		System.out.println("ConcreteColleagueB operationB");
		getMediator().change(this);
	}

	public void operation() {
		System.out.println("ConcreteColleagueB operation");
	}

}
