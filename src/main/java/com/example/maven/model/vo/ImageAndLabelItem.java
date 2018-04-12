package com.example.maven.model.vo;

import java.io.Serializable;

public class ImageAndLabelItem implements Serializable{

    private String image;

    private LabelVO labelVO;

    public ImageAndLabelItem(String image, LabelVO labelVO) {
        this.image = image;
        this.labelVO = labelVO;
    }

    public String getImage() {
        return image;
    }

    public LabelVO getLabelVO() {
        return labelVO;
    }

}
