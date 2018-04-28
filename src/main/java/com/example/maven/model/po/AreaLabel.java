package com.example.maven.model.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域标注类
 */

public class AreaLabel extends Label implements Serializable{
    private String label;
    private List<String> lineList;

    public AreaLabel(String label, List<String> lineList) {
        setType("AreaLabel");
        this.label = label;
        this.lineList = lineList;
    }

    public AreaLabel(){
        setType("AreaLabel");
        this.lineList = new ArrayList<>();
    }

    public String getLabel() {
        return label;
    }

    public List<String> getLineList() {
        return lineList;
    }

}
