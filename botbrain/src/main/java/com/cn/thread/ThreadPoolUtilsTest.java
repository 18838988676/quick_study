package com.cn.thread;

import org.junit.jupiter.api.Test;

/**
 * Copyright：botBrain.ai
 * Author: WangMingChao
 * Date: 2019/11/7.
 * Description:
 */
public class ThreadPoolUtilsTest {

    @Test
    public void test(){
        ThreadPoolUtils.newScheduledThreadPool(2).execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
}
