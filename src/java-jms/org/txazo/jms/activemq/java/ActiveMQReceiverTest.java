package org.txazo.jms.activemq.java;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.txazo.test.base.BaseTest;

public class ActiveMQReceiverTest extends BaseTest implements MessageListener {

	public ActiveMQReceiverTest() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConstant.BROKER_URL);
			Connection connection = connectionFactory.createConnection(ActiveMQConstant.USERNAME, ActiveMQConstant.PASSWORD);
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue(ActiveMQConstant.SUBJECT);
//			Destination destination = session.createQueue(ActiveMQConstant.SUBJECT);
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message msg) {
		try {
			if (msg instanceof TextMessage) {
				TextMessage textMsg = (TextMessage) msg;
				logger.info("Receive Message: {}", textMsg.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new ActiveMQReceiverTest();
	}

}
