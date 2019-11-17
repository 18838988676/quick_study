package com.cn.morethreadgetresult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test01 {

//当向线程池提交callable任务后，我们可能需要一次性获取所有返回结果，有三种处理方法。
//方法一：自己维护返回结果

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

// 存储执行结果的List
        List<Future<String>> results = new ArrayList<Future<String>>();

// 提交10个任务
        for (int i = 0; i < 10; i++) {
            Future<String> result = executorService.submit(()->{
               {
                   String name=Thread.currentThread().getName();
                    int sleepTime = new Random().nextInt(1000);
                    try {
                        Thread.sleep(sleepTime);
                    }catch (Exception e){}

                    return "线程" + name + "睡了" + sleepTime + "秒";

                }
            });
            // 将执行结果存入results中
            results.add(result);
        }

// 获取10个任务的返回结果
        for (int i = 0; i < 10; i++) {
            // 获取包含返回结果的future对象
            Future<String> future = results.get(i);
            // 从future中取出执行结果（若尚未返回结果，则get方法被阻塞，直到结果被返回为止）
            try {
                String result = future.get();
                System.out.println(result);

            }catch (Exception e){}
        }

    }

}
//此方法的弊端：
//
//需要自己创建容器维护所有的返回结果，比较麻烦；
//从list中遍历的每个Future对象并不一定处于完成状态，这时调用get()方法就会被阻塞住，如果系统是设计成每个线程完成后就能根据其结果继续做后面的事，这样对于处于list后面的但是先完成的线程就会增加了额外的等待时间。

