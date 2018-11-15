package com.mozi.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

/**
 ************************************************************
 * @类名 : RequestParam.java
 *
 * @DESCRIPTION : 公共返回参数定义
 * @DATE :  2017年3月6日
 ************************************************************
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CommonData {
    /**
     * 
     * 功能描述：请求参数
     * 
     * @return String
     *
     */
    String[] param() default {};

}
