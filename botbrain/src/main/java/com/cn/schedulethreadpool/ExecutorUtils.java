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

    //测试成功
    @Bean("executorService123456")
    public ExecutorService getScheduleThreadPoolExecutors(){
        ScheduledExecutorService scheduledExecutor= Executors.newScheduledThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"xxl-job-admin-check");
            }
        });
        return scheduledExecutor;
    }

    //测试成功  new
    @Bean("executorService123456new")
    public ExecutorService getScheduleThreadPoolExecutorsNew(){
        ScheduledExecutorService scheduledExecutor=new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"xxl-job-admin-check");
            }
        });
        return scheduledExecutor;
    }


    //测试成功
    @Bean("tCachedThreadPoolExecutor987")
    public ThreadPoolExecutor getCachedThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                int num = 0;
                return new Thread(r, "cachetest线程：" + num++);
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }


}
