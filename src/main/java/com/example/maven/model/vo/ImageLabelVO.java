package com.example.maven.model.vo;

import java.io.Serializable;

public class ImageLabelVO extends LabelVO implements Serializable{
    private String image;
    private String label;

    public ImageLabelVO(String image, String label) {
        super("ImageLabel");
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
