package com.cn.thread;

import java.util.concurrent.*;

/**
 * Copyright：botBrain.ai
 * Author: WangMingChao
 * Date: 2019/11/7.
 * Description:
 */
public class ThreadPoolUtils {

    public static ExecutorService newFixedThreadPool(int nThreads){
        return new ThreadPoolExecutor(nThreads,nThreads,5L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    }

    public static ExecutorService newCacheThreadPool(){
        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
    }

    public static ExecutorService newScheduledThreadPool(int corePoolSize){
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

}
