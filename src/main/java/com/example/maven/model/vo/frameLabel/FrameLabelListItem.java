package com.example.maven.model.vo.frameLabel;

import java.io.Serializable;

public class FrameLabelListItem implements Serializable {
    private int startX;
    private int startY;
    private int width;
    private int height;
    private String tag;

    public FrameLabelListItem(int startX, int startY, int width, int height,String tag){
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.tag = tag;
    }

    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getTag() { return tag; }
}

