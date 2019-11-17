package com.cn.morethreadgetresult;

import java.util.Random;
import java.util.concurrent.*;

public class Test03 {

//方法三：使用CompletionService
//CompletionService内部维护了一个阻塞队列，只有执行完成的任务结果才会被放入该队列，这样就确保执行时间较短的任务率先被存入阻塞队列中。

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<Future<String>>(
                10);
        //实例化CompletionService
        final CompletionService<String> completionService = new ExecutorCompletionService<String>(
                executorService, queue);

// 提交10个任务
        for ( int i=0; i<10; i++ ) {
            executorService.submit( new Callable<String>(){
                public String call(){
                    String name=Thread.currentThread().getName();
                    int sleepTime = new Random().nextInt(100);
                    try {
                        Thread.sleep(sleepTime);
                    }catch (Exception e){}

                    return "线程" + name + "睡了" + sleepTime + "秒";
                }
            } );
        }

// 输出结果
        for ( int i=0; i<10; i++ ) {
            // 获取包含返回结果的future对象（若整个阻塞队列中还没有一条线程返回结果，那么调用take将会被阻塞，当然你可以调用poll，不会被阻塞，若没有结果会返回null，poll和take返回正确的结果后会将该结果从队列中删除）
           try {
               Future<String> future = completionService.take();
               // 从future中取出执行结果，这里存储的future已经拥有执行结果，get不会被阻塞
               String result = future.get();
               System.out.println(result);

           }catch (Exception e){}
        }

    }





}
