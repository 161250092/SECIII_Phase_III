package maven.model.task;

import maven.model.label.Label;

import java.util.List;

/**
 * 发布者提供的标注样本类
 */
public class Sample {
    //样本内图片总数
    private int imageNum;
    //样本内图片 在原本的任务图片集合内的下标数组
    private List<Integer> imageIndexList;
    //发起者作的标注数组
    private List<Label> labelList;

    public int getNumber() {
        return imageNum;
    }

    public List<Integer> getImageIndexList() {
        return imageIndexList;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public Sample(int imageNum, List<Integer> imageIndexList, List<Label> labelList) {
        this.imageNum = imageNum;
        this.imageIndexList = imageIndexList;
        this.labelList = labelList;
    }
}