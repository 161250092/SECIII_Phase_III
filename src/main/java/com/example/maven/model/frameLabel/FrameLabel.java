package com.example.maven.model.frameLabel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 标框标注类
 */
public class FrameLabel implements Serializable {
    private String userId;
    private String imageId;
    private ArrayList<FrameLabelTagItem> frameLabelTagItemList;

    public FrameLabel(String userId, String imageId, ArrayList<FrameLabelTagItem> frameLabelTagItemList){
        this.userId = userId;
        this.imageId = imageId;
        this.frameLabelTagItemList = frameLabelTagItemList;
    }

    public String getImageId() { return imageId; }
    public String getUserId() { return userId; }
    public ArrayList<FrameLabelTagItem> getFrameLabelTagItemList() { return frameLabelTagItemList; }
}

