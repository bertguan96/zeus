package org.logger.strategies.anno;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 5:13]
 * Description: MongoDbStorageCondition
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(MongoDbStorageCondition.class)
public @interface ConditionalOnMongoDbStorage {
}