package com.cn.one.test;

import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.Returns;

/**
 * Copyright：botBrain.ai
 * Author: WangMingChao
 * Date: 2019/11/7.
 * Description:
 */
public class TimeStr {

    @Test
    public void getTime(){
        /*
        * while the unit of time of the return value is a millisecond,
      the granularity of the value depends on the underlying
      operating system and may be larger.  For example, many
      operating systems measure time in units of tens of
      milliseconds.
      *
      * 返回当前时间(以毫秒为单位)。
        请注意,
        虽然返回值的时间单位是毫秒，
        值的粒度取决于底层
        操作系统和可能更大。
        例如,许多
        操作系统以十为单位测量时间
        毫秒。
        * */
        System.out.println(System.currentTimeMillis());
    }
    

}
