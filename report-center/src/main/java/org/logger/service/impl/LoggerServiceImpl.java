package org.logger.service.impl;

import org.logger.entity.LoggerMessage;
import org.logger.service.LoggerService;
import org.logger.strategies.storage.StorageContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/15 23:31]
 * Description: Logger Service implements
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    private final StorageContext<LoggerMessage> storageContext;

    public LoggerServiceImpl(StorageContext<LoggerMessage> storageContext) {
        this.storageContext = storageContext;
    }

    @Override
    public void push(List<LoggerMessage> loggerMessageList) {
        storageContext.saveMany(loggerMessageList);
    }
}
