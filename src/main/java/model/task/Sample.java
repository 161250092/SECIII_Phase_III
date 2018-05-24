package model.task;

import model.po.Label;

/**
 * 发布者提供的标注样本类
 */
public class Sample {
    private int number;
    private int[] imageIndexList;
    private Label[] labelList;

    public int getNumber() {
        return number;
    }

    public int[] getImageIndexList() {
        return imageIndexList;
    }

    public Label[] getLabelList() {
        return labelList;
    }

    public Sample(int number, int[] imageIndexList, Label[] labelList) {
        this.number = number;
        this.imageIndexList = imageIndexList;
        this.labelList = labelList;
    }


}
