package org.logger.service;

import org.logger.entity.LoggerMessage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/15 23:33]
 * Description: Logger Service
 */
public interface LoggerService {
    /**
     * 推送日志列表
     *
     * @param loggerMessageList 日志列表
     */
    void push(List<LoggerMessage> loggerMessageList);

    void publishLog(List<LoggerMessage> loggerMessageList);

    void consumerLog() throws IOException, TimeoutException;
}
