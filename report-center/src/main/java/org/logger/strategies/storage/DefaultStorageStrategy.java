package org.logger.strategies.storage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 7:09]
 * Description: Default Storage Strategy
 */
@Component
@Qualifier("defaultStorageStrategy")
public class DefaultStorageStrategy<T> implements StorageStrategy<T> {

    /**
     * 新增数据
     *
     * @param object 数据对象
     */
    @Override
    public void create(Object object) {
        System.out.println("===========> default");
    }

    /**
     * 查询数据
     *
     * @param id 主键信息
     * @return 查询返回
     */
    @Override
    public T read(String id) {
        return null;
    }

    /**
     * 更新数据
     *
     * @param id     主键信息
     * @param object 更新对象
     */
    @Override
    public void update(String id, T object) {

    }

    /**
     * 删除数据
     *
     * @param id 主键信息
     */
    @Override
    public void delete(String id) {

    }

    /**
     * 软删除
     *
     * @param id 主键信息
     */
    @Override
    public void deleteSoft(String id) {

    }
}
