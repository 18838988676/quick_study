package com.cn.schedulethreadpool.job;

import java.util.concurrent.Callable;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/15.
 * @Description:
 */
public class NumAddCallable implements Callable {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        System.out.println("当前线程是："+Thread.currentThread().getName());
        System.out.println("111");
        return "ok";
    }
}
