package com.example.maven.model.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class AreaLabelVO extends LabelVO implements Serializable{
    private String image;
    private String label;
    private ArrayList<String> lineList;

    public AreaLabelVO(String image, String label, ArrayList<String> lineList) {
        super("AreaLabel");
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

    public ArrayList<String> getLineList() {
        return lineList;
    }

}
