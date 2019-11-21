package com.cn.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Test01ControllerTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("1");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("3");
    }

    @Test
    public void toStrig() {
        System.out.println("1dfads");
    }
}