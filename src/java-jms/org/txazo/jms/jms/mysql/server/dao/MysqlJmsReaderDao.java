package org.txazo.jms.jms.mysql.server.dao;

import java.util.List;

import org.txazo.jms.jms.mysql.bean.Message;

public interface MysqlJmsReaderDao {

	public List<Message> readMessage();

}
