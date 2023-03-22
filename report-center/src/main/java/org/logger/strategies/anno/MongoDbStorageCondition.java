package org.logger.strategies.anno;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 5:14]
 * Description: 
 */
public class MongoDbStorageCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return "mongo".equalsIgnoreCase(env.getProperty("storage.strategy.type"));
    }
}