package cn.com.attention.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;
import java.util.Arrays;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/7.
 * @Description:
 */
@Api(tags = "用户动态相关")
@RequestMapping("/a/{app_id}")
@Controller
public class SwaggerTest {

    @ApiOperation("点赞/取消点赞用户动态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "app_id", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "uid", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "dynamic_id", value = "动态id", required = true, dataType = "long"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "状态:0点赞1取消", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/up-or-cancel", method = RequestMethod.GET)
    public Response<Integer> upUserDynamic(@PathVariable("app_id") String appId,
                                           @RequestParam("uid") String uid,
                                           @RequestParam("dynamic_id") long dynamicId,
                                           @RequestParam("status") int status) {
        System.out.println("="+status);
        return null;
    }
}
