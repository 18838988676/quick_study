package cn.com.attention.controller;

import cn.com.attention.utils.utilsresponse.Response;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/8.
 * @Description:
 */
@Controller
@RequestMapping("/aspect")
public class AspectTest {
//http://localhost:8080/attention/aspect/t1
    @RequestMapping("/t1")
    @ResponseBody
    public Response test() {
        System.out.println("start....");
        try {
            TimeUnit.SECONDS.sleep(10);

        }catch (Exception e){

        }
        System.out.println("end....");
        return new Response("success23234");
    }

    @RequestMapping("/t2")
    @ResponseBody
    public Response tes2t() {
        System.out.println("start....");
        try {
            TimeUnit.SECONDS.sleep(10);

        }catch (Exception e){

        }
        System.out.println("end....");
        return new Response("success");
    }

}
