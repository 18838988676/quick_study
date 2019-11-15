package com.cn.card;

import java.util.concurrent.Callable;

public class Job implements Callable {

    private int num=10000;
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        System.out.println("当前线程："+Thread.currentThread().getName());
        String msg="没有票了";
        while (num!=0){
            num--;

            msg="剩余票数："+num;
         }
        System.out.println(msg);
        return msg;
    }
}
