package org.txazo.spring.email.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.txazo.framework.freemarker.util.FreeMarkerUtil;
import org.txazo.spring.email.service.SendMailService;
import org.txazo.spring.email.thread.SendMailThread;
import org.txazo.spring.email.vo.MailVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:web/WEB-INF/config/spring.xml" })
public class SpringMailTest {

	@Autowired
	private SendMailService sendMailService;

	@Test
	public void testSendSimpleMail() {
		MailVo mailVo = new MailVo();
		mailVo.setTo("784990655@qq.com");
		mailVo.setSubject("Spring Mail");
		mailVo.setText("This is a Spring Mail to txazo.");

		sendMailService.sendSimpleMail(mailVo);
	}

	@Test
	public void testSendMimeMail() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("name", "txazo");
		String html = FreeMarkerUtil.getHtml(valueMap, "template/mail.ftl");

		MailVo mailVo = new MailVo();
		mailVo.setTo("784990655@qq.com");
		mailVo.setSubject("Spring Mail");
		mailVo.setText(html);

		sendMailService.sendMimeMail(mailVo);
	}

	@Test
	public void testSendMimeMailByPool() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("name", "txazo");
		String html = FreeMarkerUtil.getHtml(valueMap, "template/mail.ftl");

		MailVo mailVo = new MailVo();
		mailVo.setTo("784990655@qq.com");
		mailVo.setSubject("Spring Mail");
		mailVo.setText(html);

		for (int i = 0; i < 5; i++) {
			ThreadPool.addThread(new SendMailThread(mailVo, sendMailService));
		}

		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class ThreadPool {

		private static ThreadPool instance = null;

		private ExecutorService pool = null;

		private ThreadPool() {
			pool = Executors.newFixedThreadPool(10);
		}

		public static ThreadPool getInstance() {
			if (instance == null) {
				synchronized (ThreadPool.class) {
					if (instance == null) {
						instance = new ThreadPool();
					}
				}
			}
			return instance;
		}

		public static void addThread(Runnable runnable) {
			getInstance().pool.execute(runnable);
		}

	}

}
