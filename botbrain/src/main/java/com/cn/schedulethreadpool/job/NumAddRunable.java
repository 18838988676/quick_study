package com.cn.schedulethreadpool.job;

import java.util.Calendar;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/15.
 * @Description:
 */
public class NumAddRunable implements Runnable {

    private volatile Integer num=1;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("当前线程是："+Thread.currentThread().getName());
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(++num);
    }
}
