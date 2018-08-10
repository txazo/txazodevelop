package org.txazo.pattern.behavior.observer.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 
 * <pre>
 * 1. 对象间的一种一对多的依赖关系，可自动通知
 * 2. 解耦观察者和目标
 * 3. 触发联动，广播通信
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ConcreteSubject implements Subject {

	private List<Observer> observers = new ArrayList<Observer>();

	@Override
	public void attach(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}

}
