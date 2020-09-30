package com.common.model;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/30
 */
public class SkipItem extends BaseModel {
    private String code;
    private String errorReason;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
}
