package com.example.maven.model.frameLabel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 标框标注类
 */
public class FrameLabel implements Serializable {
    private String imageId;
    private String userId;
    private ArrayList<FrameLabelTagItem> frameLabelTagItemList;

    public FrameLabel(String imageId, String userId, ArrayList<FrameLabelTagItem> frameLabelTagItemList){
        this.imageId = imageId;
        this.userId = userId;;
        this.frameLabelTagItemList = frameLabelTagItemList;
    }

    public String getImageId() { return imageId; }
    public String getUserId() { return userId; }
    public ArrayList<FrameLabelTagItem> getFrameLabelTagItemList() { return frameLabelTagItemList; }
}

