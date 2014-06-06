package com.ekino.technoshare.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class EmitLog {

    private static final String EXCHANGE_NAME = "pub_sub_logs";

    public static void main(String[] argv) throws Exception {
        // Connection + Channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declare exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // TO-EXPLAIN Fanout

        // Create the message and publish it
        for (String message : argv) {
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();
    }

}
