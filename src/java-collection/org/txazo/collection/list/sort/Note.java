package org.txazo.collection.list.sort;

public class Note implements Comparable<Note> {

	private int id;
	private String title;

	public Note(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(Note n) {
		int nid = n.getId();
		if (id == nid) {
			return title.compareToIgnoreCase(n.getTitle());
		}
		return id > nid ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + "\ttitle=" + title + "]";
	}

}
