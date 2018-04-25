package com.example.maven.model.po;

import java.io.Serializable;
import java.util.List;

/**
 * 整体标注类
 */
public class ImageLabel extends Label implements Serializable{

    private List<String> labelList;

    public ImageLabel(List<String> labelList) {
        setType("ImageLabel");
        this.labelList = labelList;
    }

    public ImageLabel(){
        setType("ImageLabel");
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public List<String> getLabelList() {
        return labelList;
    }

}
