package com.example.maven.model.vo;

import java.io.Serializable;

public class ImageLabelVO implements Serializable{
    private String image;
    private String label;

    public ImageLabelVO(String image, String label) {
        this.image = image;
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }


}
