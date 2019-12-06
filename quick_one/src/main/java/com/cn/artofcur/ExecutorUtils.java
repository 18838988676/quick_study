package com.cn.artofcur;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service("executorUtils")
public class ExecutorUtils {
    public ExecutorService getCacheExecutorService(){
        ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(5, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            int num=0;
            @Override
            public Thread newThread(Runnable r) {
                num++;
                return new Thread(r,"当前线程是:"+num);
            }
        });
        return threadPoolExecutor;
    }
}
