package com.example.maven.model.po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 区域标注类
 */

public class AreaLabel extends Label implements Serializable{
    private String label;
    private ArrayList<String> lineList;

    public AreaLabel(String label, ArrayList<String> lineList) {
        setType("AreaLabel");
        this.label = label;
        this.lineList = lineList;
    }

    public AreaLabel(){
        setType("AreaLabel");
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<String> getLineList() {
        return lineList;
    }

}
