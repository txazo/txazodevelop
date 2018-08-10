package org.txazo.email.java;

import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java Mail回复邮件
 * 
 * @author txazo
 * 
 */
public class ReplyMailTest extends BaseTest {

	@Test
	public void testReplyMail() throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.host", "pop.163.com");
		props.setProperty("mail.pop3.auth", "true");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("txazo1218@163.com", "528463");
			}
		});

		Store store = session.getStore();
		store.connect();

		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);

		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "txazo1218@163.com", "528463");

		Message[] messages = folder.getMessages();
		if (messages != null && messages.length > 0) {
			Message reply = null;
			for (int i = 0; i < messages.length; i++) {
				reply = messages[i].reply(false);
				reply.setFrom(new InternetAddress("txazo1218@163.com"));
				reply.setSubject("回复邮件");
				reply.setText("测试");
				reply.setSentDate(new Date());
				reply.saveChanges();
				transport.sendMessage(reply, reply.getAllRecipients());
			}
		}

		transport.close();
		folder.close(true);
		store.close();
	}

}