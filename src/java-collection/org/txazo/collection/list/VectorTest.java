package org.txazo.collection.list;

import java.util.Iterator;
import java.util.Vector;

import org.junit.Test;
import org.txazo.test.base.BaseTest;
import org.txazo.thread.annotation.ThreadSafe;

/**
 * java.util.Vector
 * 
 * <pre>
 * 1) 线程安全，原理: synchronized同步方法
 * 2) 同java.util.ArrayList
 * </pre>
 * 
 * @author txazo
 * 
 */
public class VectorTest extends BaseTest {

	@Test
	public void testVector() {
	}

	@ThreadSafe
	public static class VectorUtil {

		public static <T> T getLast(Vector<T> list) {
			/** 原子操作 */
			synchronized (list) {
				int size = list.size() - 1;
				return list.get(size);
			}
		}

		public static <T> void removeLast(Vector<T> list) {
			/** 原子操作 */
			synchronized (list) {
				int size = list.size() - 1;
				list.remove(size);
			}
		}

		/**
		 * 克隆
		 */
		public static <T> Vector<T> clone(Vector<T> list) {
			Vector<T> returnList = new Vector<T>();
			/** 原子操作 */
			synchronized (list) {
				for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
					returnList.add(iterator.next());
				}
			}
			return returnList;
		}

	}

}
