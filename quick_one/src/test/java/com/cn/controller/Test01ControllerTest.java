package com.cn.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

public class Test01ControllerTest {


//1575169200000   2019-12-01 11:00:00
//1575169500000     2019-12-01 11:05:00
    @Test
    public void toStrig() throws InterruptedException {
        LocalDate startDate =LocalDate.now();
        System.out.println(startDate);

        Thread.sleep(5000);
        LocalDate startDate2 =LocalDate.now();
        System.out.println(startDate2);
        long a1= ChronoUnit.MINUTES.between(startDate2,startDate);
        System.out.println(a1);

    }

//    // date.getTime() 返回时间的毫秒数值
//    int year = (int) ((date.getTime() - d.getTime()) / (1000 * 60 * 60 * 24)) / 365; // 计算年
//    int day = (int) ((date.getTime() - d.getTime()) / (1000 * 60 * 60 * 24)); // 计算天
//            System.out.println("您距今已生活了" + year + "年，" + "总共" + day + "天。");




    @Test
    public void toStrig2() throws InterruptedException {

        Instant inst = Instant.now();
        System.out.println("Inst0 :" + inst);
        Instant inst2 = Instant.now().plus(Duration.ofMinutes(5));

//        Instant inst2 = inst.plus(Duration.ofMinutes(0));
        System.out.println("Inst2 : " + inst2);

        System.out.println("Difference in milliseconds : " + Duration.between(inst, inst2).toMillis());

        System.out.println("Difference in seconds : " + Duration.between(inst, inst2).toMinutes());

    }
    //https://www.cnblogs.com/baobeiqi-e/p/9884726.html  Java8中计算日期时间差
}