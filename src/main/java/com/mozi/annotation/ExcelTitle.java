package com.mozi.annotation;

import java.lang.annotation.*;

/**
 * Created by MOZi on 2018/11/16.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTitle {

    String value() default "";
    int order();
}
