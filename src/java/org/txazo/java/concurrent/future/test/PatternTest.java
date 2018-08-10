package org.txazo.java.concurrent.future.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.txazo.java.concurrent.future.pattern.Client;
import org.txazo.java.concurrent.future.pattern.Data;

@RunWith(JUnit4.class)
public class PatternTest {

	@Test
	public void testFuture() throws Exception {
		Client client = new Client();
		Data data = client.request("txazo");
		String result = data.getResult();
		Assert.assertEquals("txazo", result);
	}

}
