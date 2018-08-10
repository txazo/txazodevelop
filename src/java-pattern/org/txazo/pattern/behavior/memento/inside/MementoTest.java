package org.txazo.pattern.behavior.memento.inside;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MementoTest {

	@Test
	public void testMemento() {
		Originator originator = new Originator();
		Caretaker caretaker = new Caretaker();

		originator.setState("txazo");
		// 保存备忘录
		caretaker.saveMemento(originator.createMemento());
		System.out.println(originator.getState());

		originator.setState("admin");
		System.out.println(originator.getState());

		// 恢复备忘录
		originator.restoreMemento(caretaker.getMemento());
		System.out.println(originator.getState());
	}

}
