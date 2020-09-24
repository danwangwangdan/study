package com.common.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ImageResponse {

    private String id;
    private String name;
    private boolean isFlag;

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, JSON.DEFFAULT_DATE_FORMAT,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
