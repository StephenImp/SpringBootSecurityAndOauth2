package com.mozi.controller;

import com.mozi.entity.UserSecurityEntity;
import com.mozi.exception.MoziException;
import com.mozi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MOZi on 2018/11/9.
 */
@Controller
@RequestMapping("/test")
//@Controller(value = "/test")
//@Controller
public class TestController extends BaseController{

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "connect",method = RequestMethod.GET)
    @ResponseBody
    public String testConnect() throws MoziException{

        System.out.println("测试切面日志打印");

        UserSecurityEntity wpw = userServiceImpl.findByUsername("wpw");

        System.out.println("测试Service日志打印........");

        //throw new MoziException("全局异常处理");
        return "success";

    }
}
