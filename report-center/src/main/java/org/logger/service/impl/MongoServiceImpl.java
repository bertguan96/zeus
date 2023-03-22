package org.logger.service.impl;

import org.logger.common.utils.ThreadPoolsUtil;
import org.logger.service.MongoService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/15 23:34]
 * Description:  mongob service implements
 */
@Service
public class MongoServiceImpl implements MongoService {
    private final MongoTemplate mongoTemplate;

    public MongoServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private final String poolName = "MONGO-STORAGE-POOL";
    private final ExecutorService executorService = ThreadPoolsUtil.getOrInitExecutors(poolName, 2 << 5);

    @Override
    public <T> void saveBatch(Collection<T> loggerMessageList) {
        executorService.submit(() -> {
            mongoTemplate.insertAll(loggerMessageList);
        });
        System.out.println("================>mongo save batch");
    }
}
