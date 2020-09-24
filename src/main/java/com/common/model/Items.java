package com.common.model;


/**
 * @Description:
 * @Author HuangShiming
 * @Date 2020/9/24
 */
public class Items extends BaseModel {

    private static final long serialVersionUID = 3369989503953202432L;
    private String code;
    private String name;
    private String price;

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

}
