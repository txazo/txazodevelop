package org.txazo.pattern.behavior.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer {

	private String name;

	public Reader(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable observable, Object arg) {
		System.out.println(name + " Read NewsPaper " + ((NewsPaper) observable).getTitle());
	}

}
