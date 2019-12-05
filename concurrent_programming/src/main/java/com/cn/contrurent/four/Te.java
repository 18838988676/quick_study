package com.cn.contrurent.four;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class Te {

    public static void main(String[] args) {
        Counter counter=new Counter();
        ExecutorService thread=ExecutrosUtils.getCacheThreadPoolExecutors();
        for (int i=0;i<20;i++){
            thread.submit(()->{

            };);
        }


    }


}
