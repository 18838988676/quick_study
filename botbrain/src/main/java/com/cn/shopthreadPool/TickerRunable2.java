package com.cn.shopthreadPool;

import org.springframework.util.StopWatch;

import java.util.concurrent.*;

public class TickerRunable2 implements Runnable {
    private int num = 10000;
    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;
    Object object = new Object();


    public TickerRunable2(CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
    }


    public void ticket() {
        synchronized (object) {
            if (num > 0) {
                System.out.print("当前线程是：" + Thread.currentThread().getName() + ",");
                num--;
                if (num != 0) {
                    System.out.println("剩余票数" + num);
                } else {
                    System.out.println("卖完了");
                }
            }
        }
        try {
            Thread.sleep(8);
        } catch (Exception e) {
        }
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "到达，等待中。。。");
        try {
            cyclicBarrier.await();//此处阻塞  等所有线程都到位后一起进行抢票
            if (Thread.currentThread().getName().equals("xxl-1")) {
                System.out.println("------------开始抢票了---------------");
            } else {
                Thread.sleep(10);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (num > 0) {
            ticket();
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        int threadNum = 10;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum);
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum, new ThreadFactory() {
            int i=0;
            @Override
            public Thread newThread(Runnable r) {
                i++;
                return new Thread(r,"xxl-"+i);
            }
        });

        TickerRunable2 run = new TickerRunable2(cyclicBarrier, countDownLatch);
        for (int i = 0; i < threadNum; i++) {
            executorService.submit(run);
        }

        try {
            countDownLatch.await();
            executorService.shutdown();
            stopWatch.stop();
            System.out.println("耗时：" + stopWatch.getTotalTimeSeconds() + "秒");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


}
