package org.txazo.email.java;

import java.io.*;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java Mail发送邮件
 * 
 * @author txazo
 * 
 */
public class SendMailTest extends BaseTest {

	@Test
	public void testSendMail() throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");

		/** 邮件会话 */
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("txazo1218@163.com", "528463");
			}
		});

		/** 邮件消息 */
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("txazo1218@163.com", "txazo1218"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("784990655@qq.com"));
		message.setSubject("刀塔传奇");
		message.setSentDate(new Date());

		Multipart mp = new MimeMultipart();
		BodyPart mdp1 = new MimeBodyPart();
		BodyPart mdp2 = new MimeBodyPart();

		/** 邮件正文 */
		String line = "";
		StringBuilder content = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader("D://test//in.txt"));
		while ((line = br.readLine()) != null) {
			content.append(line).append("\r\n");
		}
		br.close();
		mdp1.setContent(content.toString(), "text/html; charset=utf-8");

		/** 邮件附件 */
		File file = new File("D://MyPhoto//sidai_1.jpg");
		DataSource ds = new FileDataSource(file);
		mdp2.setDataHandler(new DataHandler(ds));
		mdp2.setFileName(MimeUtility.encodeText(file.getName()));

		mp.addBodyPart(mdp1);
		mp.addBodyPart(mdp2);
		message.setContent(mp);
		message.saveChanges();

		/** 发送邮件 */
		Transport.send(message);
	}

}