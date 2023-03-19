package org.logger.service.impl;

import org.logger.common.utils.ThreadPoolsUtil;
import org.logger.document.es.EsLoggerMessageDocument;
import org.logger.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/19 17:59]
 * Description: elastic service implements
 */
@Service
public class ElasticServiceImpl implements ElasticService {

    private final String poolName = "ELASTIC-STORAGE-POOL";
    private final ExecutorService executorService = ThreadPoolsUtil.getOrInitExecutors(poolName, 2 << 5);

    @Autowired
    ElasticsearchOperations operations;


    @Override
    public <T> void saveBatch(Collection<T> loggerMessageList) {
        IndexOperations indexOperations = operations.indexOps(EsLoggerMessageDocument.class);
        if (!indexOperations.exists()) {
            indexOperations.create();
        }

        executorService.submit(() -> {
            operations.save(loggerMessageList, IndexCoordinates.of("logger"));
        });
        System.out.println("================>es save batch");
    }
}
