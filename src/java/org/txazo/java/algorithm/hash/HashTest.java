package org.txazo.java.algorithm.hash;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class HashTest extends BaseTest {

	@Test
	public void testHash() {
		String key = "txazo";
		logger.info("{}", Hash.fnvHash(key));
		logger.info("{}", Hash.fnv1Hash(key));
		logger.info("{}", Hash.djbHash(key));
	}

	/**
	 * Hash算法
	 */
	private static class Hash {

		public static final int FNV_PRIME = 16777619;
		public static final int FNV_OFFSET_BASIS = (int) 2166136261L;
		public static final int DJB_BASIS = 5381;

		/** FNV Hash */
		public static int fnvHash(String key) {
			int hash = FNV_OFFSET_BASIS;
			for (int i = 0; i < key.length(); i++) {
				hash *= FNV_PRIME;
				hash ^= key.charAt(i);
			}
			return hash;
		}

		/** FNV1 Hash */
		public static int fnv1Hash(String key) {
			int hash = FNV_OFFSET_BASIS;
			for (int i = 0; i < key.length(); i++) {
				hash ^= key.charAt(i);
				hash *= FNV_PRIME;
			}
			return hash;
		}

		/** DJB Hash */
		public static int djbHash(String key) {
			int hash = DJB_BASIS;
			for (int i = 0; i < key.length(); i++) {
				hash = ((hash << 5) + hash) + key.charAt(i);
			}
			return hash;
		}

	}

}
