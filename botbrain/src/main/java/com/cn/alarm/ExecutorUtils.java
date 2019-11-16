package com.cn.alarm;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/14.
 * @Description: 定时线程池
 */
@Configuration("23")
public class ExecutorUtils {

    @Bean("scheduledExecutorServiceAtPackageAlarm6666668888")
    public ScheduledExecutorService getScheduledExecutorTaskforCheckOskey(){
        ScheduledExecutorService executorService= Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                int num=0;
                return new Thread(r,"check-osKey-changeThread："+num++);
            }
        });
        return  executorService;
    }

    @Bean("scheduledExecutorServiceAlarmAtPackageAlarm56548787")
    public ScheduledExecutorService getScheduledExecutorTaskforClearAlarmMails(){
        ScheduledExecutorService executorService= Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"saveAlarmForInterval-thread");
            }
        });
        return  executorService;
    }
}