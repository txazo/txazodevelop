package org.txazo.jms.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.txazo.test.base.BaseTest;

public class SpringActiveMQReceiverListener extends BaseTest implements SessionAwareMessageListener<Message> {

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		if (message instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) message;
			logger.info("Receive Message: {}", textMsg.getText());
		}
	}

}
