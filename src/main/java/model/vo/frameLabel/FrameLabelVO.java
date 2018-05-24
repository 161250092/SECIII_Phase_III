package model.vo.frameLabel;

import model.vo.LabelVO;

import java.io.Serializable;
import java.util.List;

/**
 * 标框标注所用的VO
 */
public class FrameLabelVO extends LabelVO implements Serializable {
    private String image;
    private List<FrameLabelListItemVO> labelList;

    public FrameLabelVO(String image, List<FrameLabelListItemVO> labelList){
        super(image);
        this.labelList = labelList;
    }

    public List<FrameLabelListItemVO> getLabelList() {
        return labelList;
    }
}