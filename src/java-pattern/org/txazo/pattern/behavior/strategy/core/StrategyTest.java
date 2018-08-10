package org.txazo.pattern.behavior.strategy.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StrategyTest {

	@Test
	public void testStrategy() {
		int a = 5;
		int b = 2;

		Context context = new Context();
		context.setStrategy(new AddStrategy());
		System.out.println(context.calculate(a, b));

		context.setStrategy(new MulStrategy());
		System.out.println(context.calculate(a, b));

		context.setStrategy(new SubStrategy());
		System.out.println(context.calculate(a, b));
	}

}
