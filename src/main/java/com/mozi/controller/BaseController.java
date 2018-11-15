package com.mozi.controller;


import com.mozi.constant.CommCode;
import com.mozi.entity.ResultModel;

public class BaseController {

    public <T> ResultModel<T> result(T t) {
        ResultModel<T> result = new ResultModel<T>();
        result.setData(t);
        result.setStatus(CommCode.SUCCESS_CODE);
        result.setMsg("系统处理成功");
        result.setExtField("");
        return result;
    }

}
