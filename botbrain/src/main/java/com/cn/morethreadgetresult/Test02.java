package com.cn.morethreadgetresult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test02 {
//方法二：使用ExecutorService的invokeAll函数
//本方法能解决第一个弊端，即并不需要自己去维护一个存储返回结果的容器。当我们需要获取线程池所有的返回结果时，只需调用invokeAll函数即可。
//但是，这种方式需要你自己去维护一个用于存储任务的容器。

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

// 创建存储任务的容器
        List<Callable<String>> tasks = new ArrayList<Callable<String>>();

// 提交10个任务
        for ( int i=0; i<10; i++ ) {
            Callable<String> task = new Callable<String>(){
                public String call(){
                    String name=Thread.currentThread().getName();
                    int sleepTime = new Random().nextInt(1000);
                    try {
                        Thread.sleep(sleepTime);
                    }catch (Exception e){}

                    return "线程" + name + "睡了" + sleepTime + "秒";
                }
            };
            executorService.submit( task );
            // 将task添加进任务队列
            tasks.add( task );
        }

// 获取10个任务的返回结果
        List<Future<String>> results=new ArrayList<>();
        try {
           results = executorService.invokeAll( tasks );

        }catch (Exception e){}

// 输出结果
        for ( int i=0; i<10; i++ ) {
            // 获取包含返回结果的future对象
            try {
                Future<String> future = results.get(i);
                // 从future中取出执行结果（若尚未返回结果，则get方法被阻塞，直到结果被返回为止）
                String result = future.get();
                System.out.println(result);

            }catch (Exception e){}
        }

    }

}
