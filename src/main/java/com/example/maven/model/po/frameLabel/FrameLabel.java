package com.example.maven.model.po.frameLabel;

import com.example.maven.model.po.Label;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 标框标注类
 */
public class FrameLabel extends Label implements Serializable {

    private ArrayList<FrameLabelListItem> frameLabelListItemList;

    public FrameLabel(ArrayList<FrameLabelListItem> frameLabelListItemList) {
        setType("FrameLabel");
        this.frameLabelListItemList = frameLabelListItemList;
    }

    public FrameLabel(){
        setType("FrameLabel");
    }

    public ArrayList<FrameLabelListItem> getFrameLabelListItemList() { return frameLabelListItemList; }
}

