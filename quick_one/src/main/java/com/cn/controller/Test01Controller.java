package com.cn.controller;

import com.cn.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/01")
public class Test01Controller {

    @RequestMapping("/index")
    public String toStrig() {
        User user=new User();
        user.setAddress("123");
        return "succes342s";
    }
}
