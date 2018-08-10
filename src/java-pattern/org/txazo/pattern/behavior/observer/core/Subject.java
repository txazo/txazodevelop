package org.txazo.pattern.behavior.observer.core;

public interface Subject {

	public void attach(Observer observer);

	public void detach(Observer observer);

	public void notifyObservers();

}
