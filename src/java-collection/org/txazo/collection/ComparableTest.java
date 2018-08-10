package org.txazo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.lang.Comparable, 排序接口
 * 
 * <pre>
 * 1) Arrays.sort(T[] t)
 * 2) Collections.sort(List<T> list)
 * 3) TreeMap.put(K key, V value)
 * 4) TreeSet.add(E e)
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ComparableTest extends BaseTest {

	@Test
	public void testComparable() {
		List<Score> list = new ArrayList<Score>();
		list.add(new Score(90, 80, 95));
		list.add(new Score(80, 80, 80));
		list.add(new Score(75, 85, 100));

		Collections.sort(list);

		for (int i = 0, size = list.size(); i < size; i++) {
			logger.info("{}", list.get(i));
		}
	}

	public class Score implements Comparable<Score> {

		private int chinese;
		private int math;
		private int english;

		public Score(int chinese, int math, int english) {
			this.chinese = chinese;
			this.math = math;
			this.english = english;
		}

		private int getTotalScore() {
			return chinese + math + english;
		}

		@Override
		public int compareTo(Score score) {
			int t1 = getTotalScore();
			int t2 = score.getTotalScore();
			if (t1 == t2) {
				return 0;
			}
			return t1 > t2 ? 1 : -1;
		}

		@Override
		public String toString() {
			return "Score [chinese=" + chinese + ", math=" + math + ", english=" + english + "]";
		}

	}

}
