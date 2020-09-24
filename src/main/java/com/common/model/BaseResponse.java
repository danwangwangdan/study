package com.common.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final int NO_PERMISSION = 0;
    private static final long serialVersionUID = -2913423019735105916L;
    private String msg = "success";
    private int isSuccess = SUCCESS;
    private T data;

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, JSON.DEFFAULT_DATE_FORMAT,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
