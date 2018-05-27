package maven.model.vo;

import maven.model.label.Label;
import maven.model.primitiveType.Filename;

import java.io.Serializable;
import java.util.List;

/**
 * 标注集
 */
abstract public class LabelSetVO implements Serializable {
    //图片总数
    private int taskImageNum;
    //图片名称的数组
    private List<String> filenameList;

    public int getTaskImageNum() {
        return taskImageNum;
    }

    public List<String> getFilenameList() {
        return filenameList;
    }

    public LabelSetVO(int taskImageNum, List<String> filenameList) {
        this.taskImageNum = taskImageNum;
        this.filenameList = filenameList;
    }
}
