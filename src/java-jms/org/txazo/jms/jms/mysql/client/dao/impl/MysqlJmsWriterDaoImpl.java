package org.txazo.jms.jms.mysql.client.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.txazo.jms.jms.mysql.bean.Message;
import org.txazo.jms.jms.mysql.client.dao.MysqlJmsWriterDao;

@Repository("mysqlJmsWriterDao")
public class MysqlJmsWriterDaoImpl implements MysqlJmsWriterDao {

	@Autowired
	private NamedParameterJdbcTemplate jmsNamedJdbcTemplate;

	@Override
	public boolean addMessage(Message message) {
		String sql = "insert into Message(memberId, ip, subject, text, createTime) values(?, ?, ?, ?, now())";
		int ret_s = jmsNamedJdbcTemplate.getJdbcOperations().update(sql, message.getMemberId(), message.getIp(), message.getSubject(), message.getText());
		return ret_s > 0 ? true : false;
	}

}
