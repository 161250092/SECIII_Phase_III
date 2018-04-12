package com.example.maven.model.vo.frameLabel;

import java.io.Serializable;
import java.util.List;

/**
 * 标框标注所用的VO
 */
public class FrameLabelVO implements Serializable {
    private String image;
    private List<FrameLabelListItem> labelList;

    public FrameLabelVO(String image, List<FrameLabelListItem> labelList){
        this.image = image;
        this.labelList = labelList;
    }

    public String getImage() {
        return image;
    }

    public List<FrameLabelListItem> getLabelList() {
        return labelList;
    }
}