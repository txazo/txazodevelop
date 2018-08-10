package org.txazo.pattern.creational.simplefactory.dynamic;

import java.io.InputStream;
import java.util.Properties;

public class FruitFactory {

	public static Fruit createFruit() {
		Fruit fruit = null;
		try {
			fruit = (Fruit) Class.forName(getProperty("fruit.dynamic.className")).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fruit;
	}

	public static synchronized String getProperty(String fruit) {
		Properties props = new Properties();
		InputStream is = FruitFactory.class.getResourceAsStream("/simplefactory.properties");
		try {
			props.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props.getProperty(fruit);
	}

}