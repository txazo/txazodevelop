package org.txazo.pattern.structural.adapter.extend;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Enumeration的Iterator适配器
 * 
 * Enumeration到Iterator的适配器
 * 
 * @author txazo
 * 
 */
public class Iteration<E> implements Iterator<E> {

	private Enumeration<E> enumeration;

	public Iteration(Enumeration<E> enumeration) {
		this.enumeration = enumeration;
	}

	@Override
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	@Override
	public E next() {
		return enumeration.nextElement();
	}

	@Override
	public void remove() {
	}

}