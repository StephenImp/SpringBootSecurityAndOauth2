package com.mozi.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2017/11/1.
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger  = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(public * com.mozi.controller.*.*(..))")
    public void log(){
    }


    @Before("log()")
    public void doBefore(){

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =  attributes.getRequest();

        //日志结果
        Object []logs=new String[]{
                request.getRequestURI(),
                request.getMethod(),
                request.getRemoteAddr()
        };

        logger.info("请求URL[{}]>>方法类型[{}]>>IP地址[{}]",logs);

    }

    @After("log()")
    public void doAfter(){
        logger.info("HttpAspect.doAfter");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void  doAfterRetruning(Object object){
        //logger.info("response={}",object.toString());
        logger.info("response={}", JSONObject.toJSON(object));
    }
}
