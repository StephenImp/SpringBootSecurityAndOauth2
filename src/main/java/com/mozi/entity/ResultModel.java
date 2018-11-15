package com.mozi.entity;

import java.io.Serializable;

/**
 * 
 ************************************************************
 * @类名 : ResultModel.java
 *
 * @DESCRIPTION :通用响应消息体
 * @AUTHOR : mgp
 * @DATE : 2017年8月24日
 * @param <T>
 ************************************************************
 */
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = -2693540100207312765L;

    public ResultModel() {
        status = 200;
        msg = "success";
    }

    /**
     * 响应结果数据体
     */
    private T data;

    /**
     * 响应提醒消息
     */
    private String msg;

    /**
     * 响应码（200表示成功，其它表示失败）
     */
    private long status;

    /**
     * 拓展字段
     */
    private String extField;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getExtField() {
        return extField;
    }

    public void setExtField(String extField) {
        this.extField = extField;
    }

    @Override
    public String toString() {
        return "MessageResult [data=" + data + ", msg=" + msg + ", status=" + status + ", extField=" + extField + "]";
    }

}
