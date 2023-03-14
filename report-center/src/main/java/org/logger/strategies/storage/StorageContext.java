package org.logger.strategies.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
                          StorageStrategy<T> esStorageStrategy,
                          @Qualifier("defaultStorageStrategy") StorageStrategy<T> defaultStorageStrategy) {
        storageStrategy = mongoDbStorageStrategy != null ? mongoDbStorageStrategy :
                esStorageStrategy != null ? esStorageStrategy : defaultStorageStrategy;
        if (storageStrategy == null) {
            throw new IllegalArgumentException("At least one storage strategy must not be null.");
        }
    }

    /**
     * 新增数据
     *
     * @param object 数据对象
     */

    public void create(T object) {
        storageStrategy.create(object);
    }

    /**
     * 查询数据
     *
     * @param id 主键信息
     * @return 查询返回
     */

    public T read(String id) {
        return null;
    }

    /**
     * 更新数据
     *
     * @param id     主键信息
     * @param object 更新对象
     */

    public void update(String id, T object) {

    }

    /**
     * 删除数据
     *
     * @param id 主键信息
     */

    public void delete(String id) {

    }

    /**
     * 软删除
     *
     * @param id 主键信息
     */

    public void deleteSoft(String id) {

    }


}
