package com.example.maven.model.vo;

import java.io.Serializable;
import java.util.List;

public class AreaLabelVO implements Serializable{
    private String image;
    private String label;
    private List<String> lineList;

    public AreaLabelVO(String image, String label, List<String> lineList) {
        this.image = image;
        this.label = label;
        this.lineList = lineList;
    }

    public String getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getLineList() {
        return lineList;
    }

}
