package com.example.maven.model.po;

import java.io.Serializable;

/**
 * 整体标注类
 */
public class ImageLabel extends Label implements Serializable{

    private String label;

    public ImageLabel(String taskId, String imageId, String userId, String label) {
        super(taskId, imageId, userId);
        setType("ImageLabel");
        this.label = label;
    }

    public ImageLabel(){
        setType("ImageLabel");
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
