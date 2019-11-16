package net.ddns.pzshare.messenger.rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import net.ddns.pzshare.messenger.SendException;
import net.ddns.pzshare.messenger.StartException;
import net.ddns.pzshare.messenger.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RmqWorker extends Worker {
    private static final Logger log = LogManager.getLogger();

    private final RmqConfig config;

    private Connection connection;

    private Channel channel;

    public RmqWorker(RmqConfig config){
        this.config = config;
    }

    @Override
    public void start() throws StartException{
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(config.getHost());

        if (config.getPort() != null)
            factory.setPort(config.getPort());

        log.info("Starting new rmq connection to " + config.getHost() + ":" + config.getPort());

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(config.getInQueue(), false, false, false, null);

            channel.queueDeclare(config.getOutQueue(), false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

                toSubscribers("",message);

                log.debug("Receive message with text " + message);

            };

            channel.basicConsume(config.getInQueue(), true, deliverCallback, consumerTag -> {});

        } catch (TimeoutException | IOException e) {
            throw new StartException("Failed to start rmq: " + e.getMessage());
        }
    }

    @Override
    public void stop() {
        log.info("Stopping rmq");

        try {
            connection.close();

            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void send(String userId, String msg) throws SendException {
        try {
            channel.basicPublish("", config.getOutQueue(), null, msg.getBytes());
        } catch (IOException e) {
            throw new SendException("Failed to send message" + e.getMessage());
        }
    }
}
