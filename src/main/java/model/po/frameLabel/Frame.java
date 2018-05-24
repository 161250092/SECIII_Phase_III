package model.po.frameLabel;

import java.io.Serializable;

public class Frame implements Serializable {
    private String tag;
    private int startX;
    private int startY;
    private int width;
    private int height;

    public Frame(int startX, int startY, int width, int height, String tag){
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
