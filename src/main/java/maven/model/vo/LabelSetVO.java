package maven.model.vo;

import maven.model.label.Label;
import maven.model.primitiveType.Filename;

import java.io.Serializable;
import java.util.List;

/**
 * 标注集
 */
public class LabelSetVO implements Serializable {
    //图片总数
    private int taskImageNum;
    //标注的数组
    private List<Label> labelList;
    //图片名称的数组
    private List<Filename> filenameList;

    public int getTaskImageNum() {
        return taskImageNum;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public List<Filename> getFilenameList() {
        return filenameList;
    }

    public LabelSetVO(int taskImageNum, List<Label> labelList, List<Filename> filenameList) {
        this.taskImageNum = taskImageNum;
        this.labelList = labelList;
        this.filenameList = filenameList;
    }
}
