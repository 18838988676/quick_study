package com.example.webtest.controller;

import com.example.webtest.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("{os_key}")
@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(@PathVariable("os_key")String oskey,@Valid User user){

        return oskey+"---"+user.toString();
    }
}
