package com.common.model;


/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/24
 */
public class ItemsInExcel extends BaseModel {

    private static final long serialVersionUID = 3369989503953202432L;
    private String code;
    private String name;
    private String detail;
    private String doc;
    private String price;
    private Integer isUpdatePrice = 0;
    /**
     * 0为需要更新，1为不需要更新
     */
    private Integer isNotNeedUpdate = 0;

    public ItemsInExcel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Integer getIsUpdatePrice() {
        return isUpdatePrice;
    }

    public void setIsUpdatePrice(Integer isUpdatePrice) {
        this.isUpdatePrice = isUpdatePrice;
    }

    public Integer getIsNotNeedUpdate() {
        return isNotNeedUpdate;
    }

    public void setIsNotNeedUpdate(Integer isNotNeedUpdate) {
        this.isNotNeedUpdate = isNotNeedUpdate;
    }
}
