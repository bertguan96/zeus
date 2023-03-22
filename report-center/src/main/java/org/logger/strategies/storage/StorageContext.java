package org.logger.strategies.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 5:44]
 * Description: StorageContext
 */
@Service
public class StorageContext<T> {
    private final StorageStrategy<T> storageStrategy;

    @Autowired
    public StorageContext(StorageStrategy<T> mongoDbStorageStrategy,
                          StorageStrategy<T> esStorageStrategy) {
        storageStrategy = mongoDbStorageStrategy != null ? mongoDbStorageStrategy : esStorageStrategy;
        if (storageStrategy == null) {
            throw new IllegalArgumentException("At least one storage strategy must not be null.");
        }
    }

    /**
     * 批量添加
     *
     * @param dataList 数据列表
     */
    public void saveMany(Collection<T> dataList) {
        storageStrategy.saveMany(dataList);
    }

}
