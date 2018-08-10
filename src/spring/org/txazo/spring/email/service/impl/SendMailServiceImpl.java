package org.txazo.spring.email.service.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.txazo.spring.email.vo.MailVo;
import org.txazo.spring.email.service.SendMailService;

@Service("sendMailService")
public class SendMailServiceImpl implements SendMailService {

	@Value("${email.from}")
	private String from;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendSimpleMail(MailVo mailVo) {
		String to = mailVo.getTo();
		String subject = mailVo.getSubject();
		String text = mailVo.getText();

		if (StringUtils.isNotEmpty(to) && StringUtils.isNotEmpty(from)
				&& StringUtils.isNotEmpty(subject)
				&& StringUtils.isNotEmpty(text)) {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setFrom(from);
			mail.setSubject(subject);
			mail.setText(text);

			javaMailSender.send(mail);
		}
	}

	@Override
	public void sendMimeMail(MailVo mailVo) {
		String to = mailVo.getTo();
		String subject = mailVo.getSubject();
		String text = mailVo.getText();
		Map<String, String> images = mailVo.getImages();
		Map<String, String> attachments = mailVo.getAttachments();

		if (StringUtils.isNotEmpty(to) && StringUtils.isNotEmpty(from)
				&& StringUtils.isNotEmpty(subject)
				&& StringUtils.isNotEmpty(text)) {
			MimeMessageHelper helper = null;
			MimeMessage message = javaMailSender.createMimeMessage();
			try {
				helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setTo(to);
				helper.setFrom(from);
				helper.setSubject(subject);
				helper.setText(text, true);

				if (images != null && images.size() > 0) {
					Set<Entry<String, String>> entrySet = images.entrySet();
					for (Entry<String, String> entry : entrySet) {
						String key = entry.getKey();
						String value = entry.getValue();
						if (StringUtils.isNotEmpty(key)
								&& StringUtils.isNotEmpty(value)) {
							File file = new File(value);
							if (file.exists() && file.isFile()) {
								helper.addInline(key, file);
							}
						}
					}
				}

				if (attachments != null && attachments.size() > 0) {
					Set<Entry<String, String>> entrySet = images.entrySet();
					for (Entry<String, String> entry : entrySet) {
						String key = entry.getKey();
						String value = entry.getValue();
						if (StringUtils.isNotEmpty(key)
								&& StringUtils.isNotEmpty(value)) {
							File file = new File(value);
							if (file.exists() && file.isFile()) {
								helper.addAttachment(key, file);
							}
						}
					}
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}

			javaMailSender.send(message);
		}
	}

}
