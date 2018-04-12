package com.example.maven.model.po;

import java.io.Serializable;

/**
 * 所有标签类的父类
 */
public class Label implements Serializable{
    //标注类型
    protected String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Label(){
    }
}
