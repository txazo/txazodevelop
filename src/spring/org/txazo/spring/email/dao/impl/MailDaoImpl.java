package org.txazo.spring.email.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.txazo.spring.email.dao.MailDao;
import org.txazo.spring.email.vo.MailVo;

public class MailDaoImpl implements MailDao {

	@Autowired
	private NamedParameterJdbcTemplate readJdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate writeJdbcTemplate;

	protected ParameterizedRowMapper<MailVo> mailVoRowMapper = ParameterizedBeanPropertyRowMapper
			.newInstance(MailVo.class);

	@Override
	public boolean saveMail(MailVo mailVo) {
		String sql = "insert into MailHistory(to, subject, text, isSend, sendTime) values(?, ?, ?, 0, now())";
		int result = writeJdbcTemplate.getJdbcOperations().update(sql,
				mailVo.getTo(), mailVo.getSubject(), mailVo.getText());
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<MailVo> readMail() {
		String sql = "select * from MailHistory where isSend = 0";
		List<MailVo> mailList = readJdbcTemplate.getJdbcOperations().query(sql,
				mailVoRowMapper);
		sql = "update MailHistory set isSend = 1 where isSend = 0";
		writeJdbcTemplate.getJdbcOperations().update(sql);
		return mailList;
	}

}
