package org.txazo.email.java;

import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Java Mail删除邮件
 * 
 * @author txazo
 * 
 */
public class DeleteMailTest extends BaseTest {

	@Test
	public void testDeleteMail() throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.host", "pop.163.com");
		props.setProperty("mail.pop3.auth", "true");

		/** 邮件会话 */
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("txazo1218@163.com", "528463");
			}
		});

		Store store = session.getStore();
		store.connect();

		/** 获取收件箱 */
		Folder folder = store.getFolder("INBOX");
		/** 以读写方式打开 */
		folder.open(Folder.READ_WRITE);

		Message[] messages = folder.getMessages();
		if (messages != null && messages.length > 0) {
			for (int i = 0; i < messages.length; i++) {
				logger.info("{}", messages[i].getSubject());
				// 设置邮件删除标记
				messages[i].setFlag(Flags.Flag.DELETED, true);
			}
		}

		folder.close(true);
		store.close();
	}

}