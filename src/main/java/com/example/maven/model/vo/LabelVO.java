package com.example.maven.model.vo;

import com.example.maven.model.po.Label;

abstract public class LabelVO {
    protected String type;

    public LabelVO(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
