package org.logger.strategies.storage;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 4:54]
 * Description: Storage Strategy
 */
public interface StorageStrategy<T>{
    /**
     * 新增数据
     *
     * @param object 数据对象
     */
    void create(T object);

    /**
     * 查询数据
     *
     * @param id 主键信息
     * @return 查询返回
     */
    T read(String id);

    /**
     * 更新数据
     *
     * @param id     主键信息
     * @param object 更新对象
     */
    void update(String id, T object);

    /**
     * 删除数据
     *
     * @param id 主键信息
     */
    void delete(String id);

    /**
     * 软删除
     *
     * @param id 主键信息
     */
    void deleteSoft(String id);
}
