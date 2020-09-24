package com.common.model;

import java.io.Serializable;

public class Information implements Serializable {

    private static final long serialVersionUID = 9133741786420075464L;
    private String type;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Information{" +
                "type='" + type + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
