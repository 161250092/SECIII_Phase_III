package com.example.maven.model.po.frameLabel;

import com.example.maven.model.po.Label;

import java.io.Serializable;
import java.util.List;

/**
 * 标框标注类
 */
public class FrameLabel extends Label implements Serializable {

    private List<FrameLabelListItem> frameLabelListItemList;

    public FrameLabel(List<FrameLabelListItem> frameLabelListItemList) {
        setType("FrameLabel");
        this.frameLabelListItemList = frameLabelListItemList;
    }

    public FrameLabel(){
        setType("FrameLabel");
    }

    public List<FrameLabelListItem> getFrameLabelListItemList() { return frameLabelListItemList; }
}

