package org.txazo.pattern.behavior.observer.jdk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ObserverTest {

	@Test
	public void testObserver() {
		Reader txazo = new Reader("txazo");
		Reader admin = new Reader("admin");

		NewsPaper paper = new NewsPaper();
		paper.addObserver(txazo);
		paper.addObserver(admin);

		paper.publish("One Week");

		paper.deleteObserver(admin);

		paper.publish("Two Week");
	}

}
