package org.txazo.pattern.behavior.state.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StateTest {

	@Test
	public void testState() {
		State state = new ConcreteStateA();
		Context context = new Context();
		context.setState(state);
		context.request("txazo");
	}

}
