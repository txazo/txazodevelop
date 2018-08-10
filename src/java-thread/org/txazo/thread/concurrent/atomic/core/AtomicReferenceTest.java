package org.txazo.thread.concurrent.atomic.core;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class AtomicReferenceTest extends BaseTest {

	@Test
	public void testAtomicReference() {
		AtomicReference<String> object = new AtomicReference<String>("root");

		System.out.println(object.get());
		object.set("admin");
		System.out.println(object.getAndSet("manager"));
		System.out.println(object.get());
	}

}
