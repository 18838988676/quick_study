package com.cn.artofcur;

import org.junit.runner.RunWith;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {


    @Autowired
    private ExecutorUtils executorUtils;

    private volatile int num=0;
    public void getNum(){
        num++;
        System.out.println(Thread.currentThread().getName()+",\t"+num);
    }

    @org.junit.Test
    public void tes(){
       ExecutorService ex= executorUtils.getCacheExecutorService();
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
       for (int i=0;i<200;i++) {
           ex.submit(() -> {
               getNum();
           });
       }
       stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());

        try {
            System.out.println("000");
            Thread.sleep(60000);
            System.out.println("000111");
        }catch (Exception e){}


    }


}
