package org.txazo.pattern.behavior.observer.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ObserverTest {

	@Test
	public void testObserver() {
		Subject subject = new ConcreteSubject();
		subject.attach(new ConcreteObserver("admin"));
		subject.attach(new ConcreteObserver("txazo"));
		subject.notifyObservers();
	}

}
