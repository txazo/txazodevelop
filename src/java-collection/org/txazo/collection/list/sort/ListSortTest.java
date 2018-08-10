package org.txazo.collection.list.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * List排序
 * 
 * @author txazo
 * 
 */
public class ListSortTest extends BaseTest {

	@Test
	public void testListSort() {
		List<Note> notelist = new ArrayList<Note>();
		notelist.add(new Note(3, "apple"));
		notelist.add(new Note(1, "orange"));
		notelist.add(new Note(2, "banana"));
		notelist.add(new Note(1, "apple"));

		Collections.sort(notelist);

		for (int i = 0, size = notelist.size(); i < size; i++) {
			System.out.println(notelist.get(i));
		}

		// 随机排序
		Collections.shuffle(notelist);
		for (int i = 0, size = notelist.size(); i < size; i++) {
			System.out.println(notelist.get(i));
		}

		List<Record> recordList = new ArrayList<Record>();
		recordList.add(new Record((long) 60001001, 2, 1, "2013-11-25 11:01:00"));
		recordList.add(new Record((long) 60001002, 1, 2, "2013-11-25 11:02:00"));
		recordList.add(new Record((long) 60001003, 2, 1, "2013-11-25 11:03:00"));
		recordList.add(new Record((long) 60001004, 1, 1, "2013-11-25 11:04:00"));
		recordList.add(new Record((long) 60001005, 2, 1, "2013-11-25 11:05:00"));
		recordList.add(new Record((long) 60001006, 1, 2, "2013-11-25 11:06:00"));
		recordList.add(new Record((long) 60001007, 1, 1, "2013-11-25 11:07:00"));
		recordList.add(new Record((long) 60001008, 1, 1, "2013-11-25 11:08:00"));
		recordList.add(new Record((long) 60001009, 1, 2, "2013-11-25 11:00:00"));
		recordList.add(new Record((long) 60001009, 1, 2, "2013-11-25 11:05:00"));
		recordList.add(new Record((long) 60001009, 1, 2, "2013-11-25 11:09:00"));

		Collections.sort(recordList, new RecordComparator((long) 60001009));

		for (int i = 0, size = recordList.size(); i < size; i++) {
			System.out.println(recordList.get(i));
		}
	}

}
