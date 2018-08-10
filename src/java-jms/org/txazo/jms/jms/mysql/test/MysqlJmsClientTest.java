package org.txazo.jms.jms.mysql.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.txazo.jms.jms.mysql.bean.Message;
import org.txazo.jms.jms.mysql.client.dao.MysqlJmsWriterDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:web/WEB-INF/config/spring.xml" })
public class MysqlJmsClientTest {

	@Autowired
	private MysqlJmsWriterDao mysqlJmsWriterDao;

	@Test
	public void testMysqlJmsClient() {
		Message message = new Message(Long.valueOf(62225053), "112.124.6.220", "Jms", "Message");
		mysqlJmsWriterDao.addMessage(message);
	}

}
