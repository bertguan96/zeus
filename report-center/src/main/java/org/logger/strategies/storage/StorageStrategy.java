package org.logger.strategies.storage;

import java.util.Collection;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 4:54]
 * Description: storage strategy
 */
public interface StorageStrategy<T> {
    /**
     * 批量添加
     *
     * @param dataList 数据列表
     */
    void saveMany(Collection<T> dataList);

}
