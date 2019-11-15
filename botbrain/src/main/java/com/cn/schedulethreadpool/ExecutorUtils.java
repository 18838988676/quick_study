package com.cn.schedulethreadpool;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/14.
 * @Description: 线程池
 */
@Configuration
public class ExecutorUtils {

    @Bean
    public ThreadPoolTaskExecutor check(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(50);
        threadPoolTaskExecutor.setQueueCapacity(0);
        threadPoolTaskExecutor.setThreadNamePrefix("xxl-job-admin-check");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.setKeepAliveSeconds(10);
        return threadPoolTaskExecutor;
    }

    public ExecutorService getScheduleThreadPoolExecutors(){
        ScheduledExecutorService scheduledExecutor= Executors.newScheduledThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"xxl-job-admin-check");
            }
        });
        return scheduledExecutor;
    }

}
