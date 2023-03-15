package org.logger.service;

import java.util.List;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/15 23:35]
 * Description:  MongoService
 */
public interface MongoService {

    <T> void saveBatch(List<T> loggerMessageList);
}
