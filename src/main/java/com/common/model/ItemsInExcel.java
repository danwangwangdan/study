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
    private int isUpdate = 0;

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

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
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
}
