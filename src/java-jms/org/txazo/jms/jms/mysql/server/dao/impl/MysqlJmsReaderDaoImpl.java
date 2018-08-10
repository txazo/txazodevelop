package org.txazo.jms.jms.mysql.server.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.txazo.jms.jms.mysql.bean.Message;
import org.txazo.jms.jms.mysql.server.dao.MysqlJmsReaderDao;

@Repository("mysqlJmsReaderDao")
public class MysqlJmsReaderDaoImpl implements MysqlJmsReaderDao {

	@Autowired
	private NamedParameterJdbcTemplate jmsNamedJdbcTemplate;

	private static ParameterizedRowMapper<Message> messageRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Message.class);

	private Long fetchMaxId() {
		String sql = "select max(id) from Message limit 1";
		return jmsNamedJdbcTemplate.getJdbcOperations().queryForObject(sql, Long.class);
	}

	private void cleanMessage(Long id) {
		String sql = "delete from Message where id <= ?";
		jmsNamedJdbcTemplate.getJdbcOperations().update(sql, id);
	}

	@Override
	public List<Message> readMessage() {
		List<Message> list = null;
		Long id = fetchMaxId();
		if (id == null) {
			return null;
		}
		String sql = "select id, memberId, ip, subject, text, createTime from Message where id <= ?";
		list = jmsNamedJdbcTemplate.getJdbcOperations().query(sql, messageRowMapper, id);
		cleanMessage(id);
		return list.size() > 0 ? list : null;
	}

}
