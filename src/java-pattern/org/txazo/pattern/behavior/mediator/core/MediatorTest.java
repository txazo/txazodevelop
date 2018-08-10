package org.txazo.pattern.behavior.mediator.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MediatorTest {

	@Test
	public void testMediator() {
		ConcteteMediator mediator = new ConcteteMediator();
		ConcreteColleagueA colleagueA = new ConcreteColleagueA(mediator);
		ConcreteColleagueB colleagueB = new ConcreteColleagueB(mediator);

		mediator.setColleagueA(colleagueA);
		mediator.setColleagueB(colleagueB);

		colleagueA.operationA();
		colleagueB.operationB();
	}

}
