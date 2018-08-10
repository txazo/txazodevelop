package org.txazo.jms.jms.mysql.bean;

import java.util.Date;

public class Message {

	private Long id;
	private Long memberId;
	private String ip;
	private String subject;
	private String text;
	private Date createTime;
	
	public Message(){
	}

	public Message(Long memberId, String ip, String subject, String text) {
		this.memberId = memberId;
		this.ip = ip;
		this.subject = subject;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", memberId=" + memberId + ", ip=" + ip
				+ ", subject=" + subject + ", text=" + text + ", createTime="
				+ createTime + "]";
	}

}
