package org.txazo.spring.email.thread;

import org.txazo.spring.email.service.SendMailService;
import org.txazo.spring.email.vo.MailVo;

public class SendMailThread implements Runnable {

	private MailVo mailVo;
	private SendMailService sendMailService;

	public SendMailThread(MailVo mailVo, SendMailService sendMailService) {
		this.mailVo = mailVo;
		this.sendMailService = sendMailService;
	}

	@Override
	public void run() {
		sendMailService.sendMimeMail(mailVo);
	}

}
