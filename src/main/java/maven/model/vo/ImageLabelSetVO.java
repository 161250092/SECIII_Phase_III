package maven.model.vo;

import maven.model.label.Label;
import maven.model.label.ImageLabel;
import maven.model.primitiveType.Filename;

import java.util.List;

public class ImageLabelSetVO {
    //图片总数
    private int taskImageNum;
    //标注的数组
    private List<ImageLabel> imageLabelList;
    //图片名称的数组
    private List<Filename> filenameList;

    public int getTaskImageNum() {
        return taskImageNum;
    }

    public List<ImageLabel> getImageLabelList() {
        return imageLabelList;
    }

    public List<Filename> getFilenameList() {
        return filenameList;
    }
    public ImageLabelSetVO(int taskImageNum, List<ImageLabel> imageLabelList, List<Filename> filenameList) {
        this.taskImageNum = taskImageNum;
        this.imageLabelList = imageLabelList;
        this.filenameList = filenameList;
    }




}
