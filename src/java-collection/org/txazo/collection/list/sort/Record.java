package org.txazo.collection.list.sort;

public class Record {

	private Long id;
	private Integer status;
	private Integer count;
	private String date;

	public Record(Long id, Integer status, Integer count, String date) {
		this.id = id;
		this.status = status;
		this.count = count;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return id + "\t" + status + "\t" + count + "\t" + date;
	}

}
