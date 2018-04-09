package com.example.maven.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 区域标注类
 */

public class AreaLabel extends Label implements Serializable{
    private String label;
    private ArrayList<String> lineList;

    public AreaLabel(String taskId, String imageId, String userId, String label, ArrayList<String> lineList) {
        super(taskId, imageId, userId);
        setType("AreaLabel");
        this.label = label;
        this.lineList = lineList;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<String> getLineList() {
        return lineList;
    }

}
