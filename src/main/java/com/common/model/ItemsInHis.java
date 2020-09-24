package com.common.model;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/24
 */
public class ItemsInHis extends BaseModel {

    private static final long serialVersionUID = -606086559307747393L;
    private String hisCode;
    private String hisName;
    private String hisPrice;

    public String getHisCode() {
        return hisCode;
    }

    public void setHisCode(String hisCode) {
        this.hisCode = hisCode;
    }

    public String getHisName() {
        return hisName;
    }

    public void setHisName(String hisName) {
        this.hisName = hisName;
    }

    public String getHisPrice() {
        return hisPrice;
    }

    public void setHisPrice(String hisPrice) {
        this.hisPrice = hisPrice;
    }
}
