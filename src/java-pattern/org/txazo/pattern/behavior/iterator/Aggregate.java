package org.txazo.pattern.behavior.iterator;

import java.util.Arrays;

public class Aggregate<E> implements Iterable<E> {

	private int size = 0;
	private E[] array = null;

	public Aggregate(E[] array) {
		size = array.length;
		this.array = Arrays.copyOf(array, size);
	}

	@Override
	public Iterator<E> iterator() {
		return new ConcreteIterator();
	}

	public E get(int index) {
		if (index < 0 || index >= array.length) {
			return null;
		}
		return array[index];
	}

	public int size() {
		return size;
	}

	/**
	 * 内部类实现迭代器
	 * 
	 * <pre>
	 * 1. 应用:
	 *    ArrayList内部类迭代器Itr
	 *    HashMap内部类迭代器HashIterator
	 * </pre>
	 * 
	 * @author txazo
	 * 
	 */
	private class ConcreteIterator implements Iterator<E> {

		private int cursor = 0;

		@Override
		public E next() {
			return (E) get(cursor++);
		}

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

	}

}
