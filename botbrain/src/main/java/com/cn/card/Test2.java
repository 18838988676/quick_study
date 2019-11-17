package com.cn.card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Test2 {
    public static void main(String[] args) {
        ExecutorService executorService=ExecutrosUtils.getCacheThreadPoolExecutors();
        List<Future<String>> futures=new ArrayList<>();
        Job2 job=new Job2();
        long s1=System.currentTimeMillis();
        for (int i=0;i<3;i++){
            futures.add( executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {


                    return null;
                }
            }));
        }
        long e1=System.currentTimeMillis();
        for (Future s:futures             ) {
            try {
                System.out.println(s.get());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println((e1-s1));

    }
}
