package org.txazo.collection.list.sort;

import java.util.Comparator;

public class RecordComparator implements Comparator<Record> {

	private Long topId;

	public RecordComparator(Long topId) {
		this.topId = topId;
	}

	@Override
	public int compare(Record r1, Record r2) {
		if (isNotify(r1) && isNotify(r2)) {
			return r1.getDate().compareTo(r2.getDate()) < 0 ? 1 : -1;
		} else if (isNotify(r1)) {
			return -1;
		} else if (isNotify(r2)) {
			return 1;
		}
		return compareUser(r1, r2);
	}

	private boolean isNotify(Record r) {
		return r.getId().equals(topId) && r.getStatus() == 1 && r.getCount() == 2;
	}

	private int compareUser(Record r1, Record r2) {
		if (r1.getStatus() != r2.getStatus()) {
			return r1.getStatus() > r2.getStatus() ? 1 : -1;
		}
		if (r1.getCount() != r2.getCount()) {
			return r1.getCount() < r2.getCount() ? 1 : -1;
		}
		if (r1.getDate() != r2.getDate()) {
			return r1.getDate().compareTo(r2.getDate()) < 0 ? 1 : -1;
		}
		return 0;
	}

}
