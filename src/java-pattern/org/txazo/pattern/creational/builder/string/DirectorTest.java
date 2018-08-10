package org.txazo.pattern.creational.builder.string;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DirectorTest {

	@Test
	public void testDirector() throws IOException {
		Appendable appender = new StringBuilder();
		Director director = new Director(appender);
		List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("World");
		System.out.println(director.toString());
	}

}
