package org.logger.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 7:23]
 * Description: pool tool
 */
public class ThreadPoolsUtil {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolsUtil.class);
    /**
     * 每个任务，都有自己单独的线程池
     */
    private static final Map<String, ExecutorService> EXECUTORS = new ConcurrentHashMap<>();
    /**
     * 默认线程池大小
     */
    private static final Integer DEFAULT_POOL_SIZE = 2 << 1;

    private ThreadPoolsUtil() {

    }

    /**
     * 初始化一个线程池
     *
     * @param poolName 池名称
     * @param poolSize 池大小
     * @return ExecutorService 线程池服务
     */
    private static ExecutorService init(String poolName, int poolSize) {
        final int poolSizeDealt = poolSize < DEFAULT_POOL_SIZE ? DEFAULT_POOL_SIZE : poolSize;
        // poolSizeDealt * 1.25
        int maxPoolSizeDealt = poolSizeDealt + (poolSizeDealt >> 2);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSizeDealt, maxPoolSizeDealt,
                1L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);
        // JVM钩子
        Runtime.getRuntime().addShutdownHook(
                new Thread(new FutureTask<Void>(() -> {
                    threadPoolExecutor.shutdown();
                    logger.info(String.format("钩子[%s]正在关闭线程池[%s]", Thread.currentThread().getName(), poolName));
                    return null;
                }
                ))
        );
        return threadPoolExecutor;
    }

    /**
     * 获取线程池
     *
     * @param poolName 池名称
     * @return ExecutorService 线程池服务
     */
    public static ExecutorService getOrInitExecutors(String poolName) {
        return ThreadPoolsUtil.getOrInitExecutors(poolName, DEFAULT_POOL_SIZE);
    }

    /**
     * 获取线程池
     *
     * @param poolName 池名称
     * @param poolSize 池大小
     * @return ExecutorService
     */
    public static ExecutorService getOrInitExecutors(String poolName, int poolSize) {
        return EXECUTORS.computeIfAbsent(poolName, key -> init(key, poolSize));
    }


    /**
     * 释放线程资源
     *
     * @param poolName 池名称
     */
    public static void releaseExecutors(String poolName) {
        ExecutorService executorService = EXECUTORS.remove(poolName);
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}