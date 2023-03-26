package org.logger.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import org.logger.common.utils.RabbitMqUtil;
import org.logger.entity.LoggerMessage;
import org.logger.service.LoggerService;
import org.logger.strategies.storage.StorageContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/15 23:31]
 * Description: Logger Service implements
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    private final StorageContext<LoggerMessage> storageContext;
    private final RabbitMqUtil rabbitMqUtil;

    public LoggerServiceImpl(StorageContext<LoggerMessage> storageContext,
                             RabbitMqUtil rabbitMqUtil) {
        this.storageContext = storageContext;
        this.rabbitMqUtil = rabbitMqUtil;
    }

    private static final String QUEUE_NAME = "queue-log";
    private static final String CHANNEL_NAME = "log";
    private Channel channel;

    @PostConstruct
    public void init() {
        try {
            channel = rabbitMqUtil.initChannel(CHANNEL_NAME);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        this.consumerLog();
    }

    /**
     * 消息推送
     *
     * @param loggerMessageList 日志列表
     */
    @Override
    public void push(List<LoggerMessage> loggerMessageList) {
        storageContext.saveMany(loggerMessageList);
    }


    /**
     * MQ日志消费
     */
    @Override
    public void consumerLog() {
        int CONSUMER_BATCH = 500;
        try {
            channel.basicQos(CONSUMER_BATCH);
            channel.basicConsume(QUEUE_NAME, false, (consumerTag, message) -> {
                System.out.println("<-----消息被消费---->");

                List<LoggerMessage> dataList = new LinkedList<>();
                LoggerMessage loggerMessage = JSONObject.parseObject(
                        new String(message.getBody(), StandardCharsets.UTF_8), LoggerMessage.class);
                dataList.add(loggerMessage);
                push(dataList);
                // 应答
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }, consumerTag -> System.out.println("接受失败了...!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * todo: 测试使用
     *
     * @param loggerMessageList 消息列表
     */
    @Override
    public void publishLog(List<LoggerMessage> loggerMessageList) {
        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            loggerMessageList.forEach(loggerMessage -> {
                try {
                    channel.basicPublish("", QUEUE_NAME, null, JSON.toJSONBytes(loggerMessage));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
