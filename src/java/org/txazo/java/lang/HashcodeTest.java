package org.txazo.java.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Object.hashcode()
 * 
 * <pre>
 * 1) hashcode()用在HashMap HashSet Hashtable等散列表的数据结构中
 * 2) 如果equals()相等，hashcode()一定相等
 * 3) 如果hashcode()相等，equals()不一定相等
 * 4) HashMap判断key原理: 先判断hashcode()，再判断equals()
 * </pre>
 */
public class HashcodeTest extends BaseTest {

	@Test
	public void testHashcode1() {
		Set<Hashcode1> set = new HashSet<Hashcode1>();
		set.add(new Hashcode1(100, "hashcode"));
		set.add(new Hashcode1(100, "hashcode"));
		logger.info("{}", set);

		HashMap<Hashcode1, String> map = new HashMap<Hashcode1, String>();
		map.put(new Hashcode1(100, "hashcode"), "1");
		map.put(new Hashcode1(100, "hashcode"), "2");
		logger.info("{}", map);
	}

	@Test
	public void testHashcode2() {
		Set<Hashcode2> set = new HashSet<Hashcode2>();
		set.add(new Hashcode2(100, "hashcode"));
		set.add(new Hashcode2(100, "hashcode"));
		logger.info("{}", set);

		HashMap<Hashcode2, String> map = new HashMap<Hashcode2, String>();
		map.put(new Hashcode2(100, "hashcode"), "1");
		map.put(new Hashcode2(100, "hashcode"), "2");
		logger.info("{}", map);
	}

	private class Hashcode1 {

		private int age;
		private String name;

		public Hashcode1(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "(" + age + ", " + name + ")";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Hashcode1 other = (Hashcode1) obj;
			if (name == null) {
				return other.name == null && age == other.age;
			}
			return name.equals(other.name) && age == other.age;
		}

	}

	private class Hashcode2 {

		private int age;
		private String name;

		public Hashcode2(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "(" + age + ", " + name + ")";
		}

		@Override
		public int hashCode() {
			return age ^ name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Hashcode2 other = (Hashcode2) obj;
			if (name == null) {
				return other.name == null && age == other.age;
			}
			return name.equals(other.name) && age == other.age;
		}

	}

}
