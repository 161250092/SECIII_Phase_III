package com.example.maven.model;

import java.util.ArrayList;

//区域标注类
public class AreaLabel {
    private String imageId;
    private String userId;
    private ArrayList<Line> lineSet;
    private String label;

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLineSet(ArrayList<Line> lineSet) {
        this.lineSet = lineSet;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageId() {
        return imageId;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<Line> getLineSet() {
        return lineSet;
    }

    public String getLabel() {
        return label;
    }


}
