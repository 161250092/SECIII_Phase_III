package com.example.maven.model;

import java.util.ArrayList;

//对图片进行区域标注的标注类
public class AreaLabel {
    private String imageId;
    private String userId;
    private String label;
    private ArrayList<String> lineList;

    public AreaLabel(){
        this.imageId = "";
        this.userId = "";
        this.label = "";
        this.lineList = null;
    }

    public AreaLabel(String imageId, String userId, String label, ArrayList<String> lineList) {
        this.imageId = imageId;
        this.userId = userId;
        this.label = label;
        this.lineList = lineList;
    }



    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLineList(ArrayList<String> lineList) { this.lineList = lineList; }


    public String getImageId() {
        return imageId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<String> getLineList() { return lineList; }

}
