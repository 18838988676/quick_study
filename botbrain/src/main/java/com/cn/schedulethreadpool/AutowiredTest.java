package com.cn.schedulethreadpool;

import com.cn.schedulethreadpool.job.NumAddCallable;
import com.cn.schedulethreadpool.job.NumAddRunable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/15.
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AutowiredTest {

    
   @Autowired
   private ScheduledExecutorService executorService123456;

   @Autowired
   private ExecutorService tCachedThreadPoolExecutor987;
    @Test
   public void ttt(){
        //测试通过
       System.out.println(executorService123456);
   }


   


    @org.junit.Test
    public void test() {
        executorService123456.scheduleAtFixedRate(new NumAddRunable(), 5, 3, TimeUnit.SECONDS);
        try {
            Thread.sleep(50000);
        } catch (Exception e) {
        }
    }

    @org.junit.Test
    public void test2() {
        ScheduledFuture future = executorService123456.schedule(new NumAddCallable(), 2, TimeUnit.SECONDS);
        try {
            System.out.println("当前线程是：" + Thread.currentThread().getName());//main
            System.out.println(future.get());
        } catch (Exception e) {
        }

        try {
            Thread.sleep(50000);
        } catch (Exception e) {
        }
    }

    @org.junit.Test
    public void test3() {
        ScheduledFuture future = executorService123456.scheduleAtFixedRate(() -> {
            scheduleAtFixedRateTest();
        }, 3, 3, TimeUnit.SECONDS);
        try {
            System.out.println(Thread.currentThread().getName());//main
            System.out.println(future.get());
        } catch (Exception e) {
        }

        try {
            Thread.sleep(500000);
        } catch (Exception e) {
        }
    }

    public String scheduleAtFixedRateTest() {
        System.out.println("当前线程是：" + Thread.currentThread().getName());
        return "scheduleAtFixedRateTest and future ";
    }

    @org.junit.Test
    public void test4() {
        ScheduledFuture future = executorService123456.schedule(() -> {
            scheduleAtFixedRateTest();
        }, 3, TimeUnit.SECONDS);
        try {
            System.out.println(Thread.currentThread().getName());//main
            System.out.println(future.get());
        } catch (Exception e) {
        }

        try {
            Thread.sleep(500000);
        } catch (Exception e) {
        }
    }

    public String schedule() {
        System.out.println("当前线程是：" + Thread.currentThread().getName());//main
        return "scheduleAtFixedRateTest and future ";
    }


    //getCachedThreadPoolExecutor test
    @org.junit.Test
    public void test5() {
       Future future= tCachedThreadPoolExecutor987.submit(()->{getCachedThreadPoolExecutorTest();

           try {
               Thread.sleep(5000);
           } catch (Exception e) {
           }
       });

        try {
            System.out.println(future.get());
        }catch (Exception e){}

        try {
            Thread.sleep(500000);
        } catch (Exception e) {
        }
        tCachedThreadPoolExecutor987.shutdown();
    }

    public String getCachedThreadPoolExecutorTest() {
        System.out.println("当前线程是：" + Thread.currentThread().getName());//main
        return "scheduleAtFixedRateTest and future ";
    }


}
