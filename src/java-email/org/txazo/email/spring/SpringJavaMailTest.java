package org.txazo.email.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.txazo.test.base.SpringBaseTest;

/**
 * Spring JavaMail
 * 
 * @author tuxiaozhou
 * 
 */
public class SpringJavaMailTest extends SpringBaseTest {

	@Autowired
	private JavaMailSender javaMailSender;

}
