package org.logger.strategies.storage;

import org.logger.service.impl.ElasticServiceImpl;
import org.logger.strategies.anno.ConditionalOnEsStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 4:53]
 * Description: elasticsearch storage strategy
 */
@Component
@Primary
@ConditionalOnEsStorage
@Qualifier("esStorageStrategy")
public class EsStorageStrategy<T> implements StorageStrategy<T> {

    private final ElasticServiceImpl elasticService;

    public EsStorageStrategy(ElasticServiceImpl elasticService) {
        this.elasticService = elasticService;
    }

    /**
     * 批量添加
     *
     * @param dataList 数据列表
     */
    @Override
    public void saveMany(Collection<T> dataList) {
        elasticService.saveBatch(dataList);
    }
}