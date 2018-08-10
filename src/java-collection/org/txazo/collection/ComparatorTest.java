package org.txazo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * java.util.Comparator
 * 
 * <pre>
 * 1) Arrays.sort(T[] a, Comparator<? super T> c)
 * 2) Collections.sort(List<T> list, Comparator<? super T> c)
 * 3) TreeMap.TreeMap(Comparator<? super K> comparator)
 * 4) TreeSet.TreeSet(Comparator<? super E> comparator)
 * </pre>
 * 
 * @author txazo
 * 
 */
public class ComparatorTest extends BaseTest {

	@Test
	public void testComparator() {
		List<Score> list = new ArrayList<Score>();
		list.add(new Score(90, 80, 95));
		list.add(new Score(80, 80, 80));
		list.add(new Score(75, 85, 100));

		Collections.sort(list, new ScoreComparator());

		for (int i = 0, size = list.size(); i < size; i++) {
			logger.info("{}", list.get(i));
		}
	}

	public class Score {

		private int chinese;
		private int math;
		private int english;

		public Score(int chinese, int math, int english) {
			this.chinese = chinese;
			this.math = math;
			this.english = english;
		}

		public int getTotalScore() {
			return chinese + math + english;
		}

		@Override
		public String toString() {
			return "Score [chinese=" + chinese + ", math=" + math + ", english=" + english + "]";
		}

	}

	public class ScoreComparator implements Comparator<Score> {

		@Override
		public int compare(Score s1, Score s2) {
			int t1 = s1.getTotalScore();
			int t2 = s2.getTotalScore();
			if (t1 == t2) {
				return 0;
			}
			return t1 > t2 ? 1 : -1;
		}

	}

}
