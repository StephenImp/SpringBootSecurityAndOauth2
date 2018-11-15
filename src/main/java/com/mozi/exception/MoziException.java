package com.mozi.exception;

import com.mozi.constant.CommCode;

/**
 * 
 ************************************************************
 * @类名 : EzgoException.java
 *
 * @DESCRIPTION :异常处理
 * @AUTHOR : mgp
 * @DATE : 2017年8月24日
 ************************************************************
 */
public class MoziException extends Exception {

    private static final long serialVersionUID = 2761412840375310710L;

    private long code;

    public MoziException(String message) {
        super(message);
        code = CommCode.FAILURE_CODE;
    }

    public MoziException(long code, String message) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }

}
