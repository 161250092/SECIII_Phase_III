package com.example.maven.model.frameLabel;

import com.example.maven.model.Label;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 标框标注类
 */
public class FrameLabel extends Label implements Serializable {

    private ArrayList<FrameLabelTagItem> frameLabelTagItemList;

    public FrameLabel(String taskId, String imageId, String userId, ArrayList<FrameLabelTagItem> frameLabelTagItemList) {
        super(taskId, imageId, userId);
        setType("FrameLabel");
        this.frameLabelTagItemList = frameLabelTagItemList;
    }

    public ArrayList<FrameLabelTagItem> getFrameLabelTagItemList() { return frameLabelTagItemList; }
}

