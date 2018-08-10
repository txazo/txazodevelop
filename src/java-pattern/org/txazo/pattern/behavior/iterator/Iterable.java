package org.txazo.pattern.behavior.iterator;

/**
 * 迭代器声明
 * 
 * @author txazo
 * 
 * @param <E>
 */
public interface Iterable<E> {

	public Iterator<E> iterator();

}
