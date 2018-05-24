package model.po.frameLabel;

import model.po.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 标框标注类
 */
public class FrameLabel extends Label implements Serializable {

    private List<FrameLabelListItem> labelList;

    public FrameLabel(List<FrameLabelListItem> frameLabelListItemList) {
        setType("FrameLabel");
        this.labelList = frameLabelListItemList;
    }

    public FrameLabel(){
        setType("FrameLabel");
        this.labelList = new ArrayList<FrameLabelListItem>();
    }

    public List<FrameLabelListItem> getLabelList() { return labelList; }
}

