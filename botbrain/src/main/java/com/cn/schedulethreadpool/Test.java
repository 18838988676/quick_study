package com.cn.schedulethreadpool;

import com.cn.schedulethreadpool.job.NumAddCallable;
import com.cn.schedulethreadpool.job.NumAddRunable;

import java.util.concurrent.*;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/15.
 * @Description:
 */
public class Test {

    public ScheduledExecutorService getScheduleThreadPoolExecutors() {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "xxl-job-admin-check");
            }
        });
        return scheduledExecutor;
    }

    public ThreadPoolExecutor getCachedThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                int num = 0;
                return new Thread(r, "cachetest线程：" + num++);
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }


    @org.junit.Test
    public void test() {
        ScheduledExecutorService executorService = getScheduleThreadPoolExecutors();
        executorService.scheduleAtFixedRate(new NumAddRunable(), 5, 3, TimeUnit.SECONDS);
        try {
            Thread.sleep(50000);
        } catch (Exception e) {
        }
    }

    @org.junit.Test
    public void test2() {
        ScheduledExecutorService executorService = getScheduleThreadPoolExecutors();
        ScheduledFuture future = executorService.schedule(new NumAddCallable(), 2, TimeUnit.SECONDS);
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
        ScheduledExecutorService executorService = getScheduleThreadPoolExecutors();
        ScheduledFuture future = executorService.scheduleAtFixedRate(() -> {
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
        ScheduledExecutorService executorService = getScheduleThreadPoolExecutors();
        ScheduledFuture future = executorService.schedule(() -> {
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
       ThreadPoolExecutor threadPoolExecutor=getCachedThreadPoolExecutor();
       Future future= threadPoolExecutor.submit(()->{getCachedThreadPoolExecutorTest();

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
        threadPoolExecutor.shutdown();
    }

    public String getCachedThreadPoolExecutorTest() {
        System.out.println("当前线程是：" + Thread.currentThread().getName());//main
        return "scheduleAtFixedRateTest and future ";
    }


}
