package org.txazo.collection.list.traverse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class ListTraverseUtil extends BaseTest {

	public static void traverse(List<?> list, ListTraverseCallback callback) {
		if (list instanceof RandomAccess) {
			for (int i = 0, n = list.size(); i < n; i++) {
				callback.callback(list.get(i));
			}
		} else {
			for (Iterator<?> i = list.iterator(); i.hasNext();) {
				callback.callback(i.next());
			}
		}
	}

	public interface ListTraverseCallback {

		public void callback(Object obj);

	}

	@Test
	public void testListTraverse() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		ListTraverseUtil.traverse(list, new ListTraverseCallback() {

			@Override
			public void callback(Object obj) {
				System.out.println(obj.toString());
			}

		});
	}

}
