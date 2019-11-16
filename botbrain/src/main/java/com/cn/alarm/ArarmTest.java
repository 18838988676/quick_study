package com.cn.alarm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/11/16.
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArarmTest {

    @Autowired
    private IntervalAlarmService intervalAlarmService898978151510;

    //如果IntervalAlarmService 的实现类即：IntervalAlarmServiceImpl上只写了注解Configuration，
    // 则 IntervalAlarmService 与 intervalAlarmServiceImpl和 IntervalAlarmServiceImpl  都可以注入，与首字母大小写无关；
    //如果IntervalAlarmServiceImpl上写了注解Configuration("任意。。。")，则注入时的名字就是也任意。。。一致！！！

    @Test
    public void test(){
        System.out.println(intervalAlarmService898978151510);
    }


}
