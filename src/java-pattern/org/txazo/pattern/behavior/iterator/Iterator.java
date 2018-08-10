package org.txazo.pattern.behavior.iterator;

/**
 * 迭代器接口
 * 
 * <pre>
 * 1. 定义访问和遍历元素的接口
 * 2. 应用:
 *    java.util.Iterator
 * </pre>
 * 
 * @author txazo
 * 
 */
public interface Iterator<E> {

	public E next();

	public boolean hasNext();

}
