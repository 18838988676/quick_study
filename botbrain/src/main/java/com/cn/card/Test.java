package com.cn.card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        ExecutorService executorService=ExecutrosUtils.getCacheThreadPoolExecutors();
        List<Future> futures=new ArrayList<>();
        for (int i=0;i<5;i++){
            futures.add( executorService.submit(new Job()));
        }
        for (Future s:futures             ) {
            try {
                System.out.println(s.get());
            }catch (Exception e){}
        }


    }
}
