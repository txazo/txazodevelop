package org.txazo.thread.concurrent.atomic.core;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AtomicIntegerFieldUpdaterTest extends BaseTest {

	@Test
	public void testAtomicIntegerFieldUpdater() {
		Person person = new Person(1);
		AtomicIntegerFieldUpdater<Person> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "id");

		System.out.println(fieldUpdater.get(person));
		fieldUpdater.set(person, 5);
		System.out.println(fieldUpdater.getAndSet(person, 10));
		System.out.println(fieldUpdater.get(person));
	}

	public class Person {

		public volatile int id;

		public Person(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

}
