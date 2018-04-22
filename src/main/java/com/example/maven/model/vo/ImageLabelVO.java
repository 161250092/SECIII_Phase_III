package com.example.maven.model.vo;

import java.io.Serializable;
import java.util.List;

public class ImageLabelVO implements Serializable{
    private String image;
    private List<String> labelList;

    public ImageLabelVO(String image, List<String> labelList) {
        this.image = image;
        this.labelList = labelList;
    }

    public String getImage() {
        return image;
    }

    public List<String> getLabel() {
        return labelList;
    }

}
