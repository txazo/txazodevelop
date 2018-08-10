package org.txazo.spring.email.vo;

import java.util.Map;

public class MailVo {

	private String to = null;
	private String subject = null;
	private String text = null;
	private Map<String, String> images = null;
	private Map<String, String> attachments = null;

	public MailVo() {
	}

	public MailVo(String to, String subject, String text) {
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public MailVo(String to, String subject, String text,
			Map<String, String> images, Map<String, String> attachments) {
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.images = images;
		this.attachments = attachments;
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

	public Map<String, String> getImages() {
		return images;
	}

	public void setImages(Map<String, String> images) {
		this.images = images;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

}
