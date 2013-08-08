package ru.smply.mq.purger;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class Runner {
    public static void main(String[] args) throws Exception {
        String host = System.getProperty("host");
        int port = Integer.valueOf(System.getProperty("port"));
        String queueManager = System.getProperty("queueManager");
        String channel = System.getProperty("channel");
        String queue = System.getProperty("queue");

        System.out.println("Creating connection to MQ at " + host + ":" + port);
        System.out.println("using queue manager: " + queueManager + " and channel: " + channel);

        MQConnectionFactory connectionFactory = new MQConnectionFactory();
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setQueueManager(queueManager);
        connectionFactory.setChannel(channel);
        connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        System.out.println("Connecting to a queue: " + queue);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer messageConsumer = session.createConsumer(session.createQueue(queue));

        int messageCount = 0;
        while(true) {
            System.out.println("Waiting for a message");
            messageConsumer.receive();
            System.out.println(messageCount + " messages received");

            messageCount++;
        }
    }
}
