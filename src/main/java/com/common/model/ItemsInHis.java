package com.common.model;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/24
 */
public class ItemsInHis extends BaseModel {

    private static final long serialVersionUID = -606086559307747393L;
    private String hisId;
    private String hisCode;
    private String hisName;
    private String hisPrice;
    private String hisDetail;
    private String hisDoc;
    private Integer isUpdate;
    private String baseExcelPrice;

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


    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getBaseExcelPrice() {
        return baseExcelPrice;
    }

    public void setBaseExcelPrice(String baseExcelPrice) {
        this.baseExcelPrice = baseExcelPrice;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public String getHisDetail() {
        return hisDetail;
    }

    public void setHisDetail(String hisDetail) {
        this.hisDetail = hisDetail;
    }

    public String getHisDoc() {
        return hisDoc;
    }

    public void setHisDoc(String hisDoc) {
        this.hisDoc = hisDoc;
    }
}
