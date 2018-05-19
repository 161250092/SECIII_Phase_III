package com.example.maven.model.task;

import com.example.maven.model.po.Label;

/**
 * 发布者提供的标注样本类
 */
public class Sample {
    private int[] imageIndexList;
    private Label[] labelList;

    public void setImageIndexList(int[] imageIndexList) {
        this.imageIndexList = imageIndexList;
    }

    public void setLabelList(Label[] labelList) {
        this.labelList = labelList;
    }

    public int[] getImageIndexList() {
        return imageIndexList;
    }

    public Label[] getLabelList() {
        return labelList;
    }

    public Sample(int[] imageIndexList, Label[] labelList) {
        this.imageIndexList = imageIndexList;
        this.labelList = labelList;
    }


}
