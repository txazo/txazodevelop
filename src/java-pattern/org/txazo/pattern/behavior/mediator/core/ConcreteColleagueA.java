package org.txazo.pattern.behavior.mediator.core;

public class ConcreteColleagueA extends Colleague {

	public ConcreteColleagueA(Mediator mediator) {
		super(mediator);
	}

	public void operationA() {
		System.out.println("ConcreteColleagueA operationA");
		getMediator().change(this);
	}

	public void operation() {
		System.out.println("ConcreteColleagueA operation");
	}

}
