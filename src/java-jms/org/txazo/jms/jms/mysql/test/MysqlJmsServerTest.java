package org.txazo.jms.jms.mysql.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.txazo.jms.jms.mysql.bean.Message;
import org.txazo.jms.jms.mysql.server.dao.MysqlJmsReaderDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:web/WEB-INF/config/spring.xml" })
public class MysqlJmsServerTest {

	@Autowired
	private MysqlJmsReaderDao mysqlJmsReaderDao;

	@Test
	public void testMysqlJmsServer() {
		List<Message> list = mysqlJmsReaderDao.readMessage();
		if (list != null && list.size() > 0) {
			for (Message message : list) {
				System.out.println(message);
			}
		}
	}

}
