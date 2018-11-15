package com.mozi.handle;

import com.mozi.constant.CommCode;
import com.mozi.entity.ResultModel;
import com.mozi.exception.MoziException;
import com.mozi.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获异常
 * 控制层的异常抛到这里
 * Created by admin on 2017/11/1.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel handle(Exception e){

        if(e instanceof MoziException){
            MoziException moziException = (MoziException)e;
            return  ResultUtil.error(moziException.getCode(),e.getMessage());
        }

        logger.info("系统内部错误！");
        return ResultUtil.error(-1,"系统内部错误！");
    }
}
