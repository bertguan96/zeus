package org.logger.strategies.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void saveMany(List<T> dataList) {
        storageStrategy.saveMany(dataList);
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
