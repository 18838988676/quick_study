package com.cn.contrurent.four;

import java.util.concurrent.*;

public class ExecutrosUtils {

    public static ExecutorService getCacheThreadPoolExecutors(){
        ThreadPoolExecutor executorService= new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            int num=0;
            public Thread newThread(Runnable r) {
                num++;
                return new Thread(r,"线程"+num);
            }
        });
        return executorService;
    }

}
