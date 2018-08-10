package org.txazo.jms.jms.mysql.client.dao;

import org.txazo.jms.jms.mysql.bean.Message;

public interface MysqlJmsWriterDao {

	public boolean addMessage(Message message);

}
