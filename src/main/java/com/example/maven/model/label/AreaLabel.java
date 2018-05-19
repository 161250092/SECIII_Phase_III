package com.example.maven.model.label;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域标注类
 */

public class AreaLabel extends Label implements Serializable{
    private List<String> labelList;
    private List<String> lineList;

    public AreaLabel(List<String> labelList, List<String> lineList) {
        setType("AreaLabel");
        this.labelList = labelList;
        this.lineList = lineList;
    }

    public AreaLabel(){
        setType("AreaLabel");
        this.labelList = new ArrayList<>();
        this.lineList = new ArrayList<>();
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public List<String> getLineList() {
        return lineList;
    }

}
