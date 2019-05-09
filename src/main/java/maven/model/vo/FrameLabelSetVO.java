package maven.model.vo;

import maven.model.label.frameLabel.FrameLabel;
import maven.model.primitiveType.Filename;

import java.util.List;

public class FrameLabelSetVO extends LabelSetVO{
    //标注的数组
    private List<FrameLabel> labelList;

    public List<FrameLabel> getLabelList() {
        return labelList;
    }

    public FrameLabelSetVO(int taskImageNum, List<FrameLabel> labelList, List<String> filenameList) {
        super(taskImageNum,filenameList);
        this.labelList = labelList;
    }
}
