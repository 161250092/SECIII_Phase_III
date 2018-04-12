package com.example.maven.model.po.frameLabel;

import com.example.maven.model.po.Label;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 标框标注类
 */
public class FrameLabel extends Label implements Serializable {

    private ArrayList<FrameLabelTagItem> frameLabelTagItemList;

    public FrameLabel(ArrayList<FrameLabelTagItem> frameLabelTagItemList) {
        setType("FrameLabel");
        this.frameLabelTagItemList = frameLabelTagItemList;
    }

    public FrameLabel(){
        setType("FrameLabel");
    }

    public ArrayList<FrameLabelTagItem> getFrameLabelTagItemList() { return frameLabelTagItemList; }
}

