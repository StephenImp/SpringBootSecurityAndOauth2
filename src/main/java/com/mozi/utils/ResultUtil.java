package com.mozi.utils;


import com.mozi.constant.CommCode;
import com.mozi.entity.ResultModel;

/**
 * Created by admin on 2017/11/1.
 */
public class ResultUtil {

    public static ResultModel success(Object object){

        ResultModel result = new ResultModel();
        result.setStatus(CommCode.SUCCESS_CODE);
        result.setMsg("成功");
        result.setData(object);

        return result;
    }

    public static ResultModel success(){
        return success(null);
    }


    public static ResultModel error(long code,String msg){

        ResultModel result = new ResultModel();
        result.setStatus(code);
        result.setMsg(msg);

        return result;
    }

}
