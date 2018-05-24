package model.vo;

import java.io.Serializable;
import java.util.List;

public class AreaLabelVO extends LabelVO implements Serializable{
    private List<String> labelList;
    private List<String> lineList;

    public AreaLabelVO(String image, List<String> labelList, List<String> lineList) {
        super(image);
        this.labelList = labelList;
        this.lineList = lineList;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public List<String> getLineList() {
        return lineList;
    }

}
