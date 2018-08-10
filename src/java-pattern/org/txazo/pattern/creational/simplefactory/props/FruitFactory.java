package org.txazo.pattern.creational.simplefactory.props;

import java.io.InputStream;
import java.util.Properties;

public class FruitFactory {

	private static String fruitClass = null;

	static {
		Properties props = new Properties();
		InputStream is = FruitFactory.class.getResourceAsStream("/simplefactory.properties");
		try {
			props.load(is);
			is.close();

			fruitClass = props.getProperty("fruit.props.className");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Fruit createFruit() {
		Fruit fruit = null;
		try {
			fruit = (Fruit) Class.forName(fruitClass).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fruit;
	}

}