package org.logger.strategies.storage;

import org.logger.service.MongoService;
import org.logger.strategies.anno.ConditionalOnMongoDbStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 4:53]
 * Description: mongodb storage strategy
 */
@Component
@Primary
@ConditionalOnMongoDbStorage
@Qualifier("mongoDbStorageStrategy")
public class MongoDbStorageStrategy<T> implements StorageStrategy<T> {
    private final MongoService mongoService;

    @Autowired
    public MongoDbStorageStrategy(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    /**
     * 批量添加
     *
     * @param dataList 数据列表
     */
    @Override
    public void saveMany(Collection<T> dataList) {
        mongoService.saveBatch(dataList);
    }
}