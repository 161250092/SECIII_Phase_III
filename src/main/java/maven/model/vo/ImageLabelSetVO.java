package maven.model.vo;

import maven.model.label.Label;
import maven.model.label.ImageLabel;
import maven.model.primitiveType.Filename;

import java.util.List;

public class ImageLabelSetVO extends LabelSetVO{
    //标注的数组
    private List<ImageLabel> labelList;

    public List<ImageLabel> getLabelList() {
        return labelList;
    }

    public ImageLabelSetVO(int taskImageNum, List<ImageLabel> labelList, List<String> filenameList) {
        super(taskImageNum,filenameList);
        this.labelList = labelList;
    }
}
