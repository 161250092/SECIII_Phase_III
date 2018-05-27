package maven.model.vo;

import maven.model.label.Label;
import maven.model.label.areaLabel.AreaLabel;
import maven.model.primitiveType.Filename;

import java.util.List;

public class AreaLabelSetVO extends LabelSetVO{
    //标注的数组
    private List<AreaLabel> labelList;

    public List<AreaLabel> getLabelList() {
        return labelList;
    }

    public AreaLabelSetVO(int taskImageNum, List<Filename> filenameList, List<AreaLabel> labelList) {
        super(taskImageNum,filenameList);
        this.labelList = labelList;
    }

}
