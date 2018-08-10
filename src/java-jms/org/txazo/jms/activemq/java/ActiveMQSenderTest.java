package org.txazo.jms.activemq.java;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.txazo.test.base.BaseTest;

public class ActiveMQSenderTest extends BaseTest {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConstant.BROKER_URL);
		Connection connection = connectionFactory.createConnection(ActiveMQConstant.USERNAME, ActiveMQConstant.PASSWORD);
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = new ActiveMQQueue(ActiveMQConstant.SUBJECT);
//		Destination destination = session.createQueue(ActiveMQConstant.SUBJECT);
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		Message message = session.createTextMessage("message");
		producer.send(message);

		session.close();
		connection.close();
	}

}
