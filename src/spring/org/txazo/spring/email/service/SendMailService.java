package org.txazo.spring.email.service;

import org.txazo.spring.email.vo.MailVo;

/**
 * 发送邮件
 * 
 * @author tuxiaozhou
 * 
 */
public interface SendMailService {

	public void sendSimpleMail(MailVo mailVo);

	public void sendMimeMail(MailVo mailVo);

}
