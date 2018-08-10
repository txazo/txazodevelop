package org.txazo.pattern.behavior.observer.jdk;

import java.util.Observable;

/**
 * JDK Observer观察者模式
 * 
 * @author txazo
 * 
 */
public class NewsPaper extends Observable {

	private String title;

	public void publish(String title) {
		this.title = title;
		setChanged();
		notifyObservers();
	}

	public String getTitle() {
		return title;
	}

}
