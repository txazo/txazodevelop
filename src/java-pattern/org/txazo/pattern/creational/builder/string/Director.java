package org.txazo.pattern.creational.builder.string;

import java.io.IOException;
import java.util.List;

public class Director {

	private Appendable appender;

	public Director(Appendable appender) {
		this.appender = appender;
	}

	public void build(List<String> list) throws IOException {
		if (appender == null || list == null) {
			return;
		}

		int size = list.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				appender.append(list.get(i));
			}
		}
	}

	public String toString() {
		return appender.toString();
	}

}
