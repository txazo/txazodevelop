package org.txazo.jms.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.txazo.test.base.SpringBaseTest;

public class SpringActiveMQTest extends SpringBaseTest {

	@Autowired
	public JmsTemplate activemqJmsTemplate;

	public void sendMessage(final String message) {
		activemqJmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}

		});
	}

	public void receiveMessage() {
		try {
			Message msg = activemqJmsTemplate.receive();
			if (msg instanceof TextMessage) {
				TextMessage textMsg = (TextMessage) msg;
				logger.info("Receive Message: " + textMsg.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMessage() {
		sendMessage("Spring ActiveMQ");
	}

	@Test
	public void testReceiveMessage() {
		receiveMessage();
	}

}
