package org.logger.common.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMqUtil {
    private final ConnectionFactory connectionFactory;
    private final String defaultConnectName = "Consumers-" + LocalDateTime.now().getNano();

    public RabbitMqUtil() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.200");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
    }

    /**
     * 初始化 Channel
     *
     * @return Channel
     *
     * @throws IOException      IO
     * @throws TimeoutException Time
     */
    public Channel initChannel() throws IOException, TimeoutException {
        String defaultConnectName = "Consumers-" + LocalDateTime.now().getNano();
        return initChannel(defaultConnectName);
    }

    /**
     * 初始化 Channel
     *
     * @param connectName 连接名
     * @return Channel
     *
     * @throws IOException      IO
     * @throws TimeoutException Time
     */
    public Channel initChannel(String connectName) throws IOException, TimeoutException {
        String dealConnectName = connectName.isBlank() ? defaultConnectName : connectName;
        Connection connection = connectionFactory.newConnection(dealConnectName);
        return connection.createChannel();
    }
}
