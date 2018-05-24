package model.task;

import model.label.Label;

import java.util.List;

/**
 * 发布者提供的标注样本类
 */
public class Sample {
    private int number;
    private List<Integer> imageIndexList;
    private List<Label> labelList;

    public int getNumber() {
        return number;
    }

    public List<Integer> getImageIndexList() {
        return imageIndexList;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public Sample(int number, List<Integer> imageIndexList, List<Label> labelList) {
        this.number = number;
        this.imageIndexList = imageIndexList;
        this.labelList = labelList;
    }
}
