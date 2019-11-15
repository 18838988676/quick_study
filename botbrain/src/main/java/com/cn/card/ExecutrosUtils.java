package com.cn.card;

import java.util.concurrent.*;

public class ExecutrosUtils {

    public static ExecutorService getCacheThreadPoolExecutors(){
        ExecutorService executorService= new ThreadPoolExecutor(3, 10, 30, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                int num=0;
                return new Thread(r,"我是线程"+num++);
            }
        });
        return executorService;
    }

}
