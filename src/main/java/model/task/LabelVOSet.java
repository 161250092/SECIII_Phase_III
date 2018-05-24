package model.task;

import model.vo.LabelVO;

/**
 * 标注集
 */
public class LabelVOSet {
    private int number;
    private LabelVO[] labelVOList;

    public int getNumber() {
        return number;
    }

    public LabelVO[] getLabelList() {
        return labelVOList;
    }

    public LabelVOSet(int number, LabelVO[] labelList) {
        this.number = number;
        this.labelVOList = labelList;
    }
}
