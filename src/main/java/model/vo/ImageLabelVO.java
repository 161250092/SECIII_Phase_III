package model.vo;

import java.io.Serializable;
import java.util.List;

public class ImageLabelVO extends LabelVO implements Serializable{
    private List<String> labelList;

    public ImageLabelVO(String image, List<String> labelList) {
        super(image);
        this.labelList = labelList;
    }

    public List<String> getLabel() {
        return labelList;
    }

}
