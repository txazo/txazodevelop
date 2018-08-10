package org.txazo.pattern.structural.adapter.extend;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Iterator的Enumeration适配器
 * 
 * Iterator到Enumeration的适配器
 * 
 * @author txazo
 * 
 */
public class Enumerator<E> implements Enumeration<E> {

	private Iterator<E> iterator;

	public Enumerator(Iterator<E> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public E nextElement() {
		return iterator.next();
	}

}