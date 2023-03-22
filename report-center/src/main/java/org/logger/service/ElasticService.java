package org.logger.service;

import java.util.Collection;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/15 23:35]
 * Description:  ElasticService
 */
public interface ElasticService {

    <T> void saveBatch(Collection<T> loggerMessageList);
}
