package model.vo;

import model.label.Label;
import model.primitiveType.Filename;

/**
 * 标注集
 */
public class LabelSetVO {
    //图片总数
    private int taskImageNum;
    //标注的数组
    private Label[] labelList;
    //图片名称的数组
    private Filename[] filenameList;

    public int getTaskImageNum() {
        return taskImageNum;
    }

    public Label[] getLabelList() {
        return labelList;
    }

    public Filename[] getFilenameList() {
        return filenameList;
    }

    public LabelSetVO(int taskImageNum, Label[] labelList, Filename[] filenameList) {
        this.taskImageNum = taskImageNum;
        this.labelList = labelList;
        this.filenameList = filenameList;
    }
}
