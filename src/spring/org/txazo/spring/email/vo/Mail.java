package org.txazo.spring.email.vo;

import java.sql.Date;

public class Mail {

	private Long id;
	private String to = null;
	private String subject = null;
	private String text = null;
	private int isSend = 0;
	private Date sendTimeDate = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public Date getSendTimeDate() {
		return sendTimeDate;
	}

	public void setSendTimeDate(Date sendTimeDate) {
		this.sendTimeDate = sendTimeDate;
	}

}
