package com.example.maven.model;

public class Tag {
    //图片ID
    private String imageID;
    //用户ID
    private String userID;
    //标签类型 -- 方框标注 && 区域标注
    private String tagType;
    //标签编号
    private String tagID;
    //标签内容
    private String tagContent;
    //标注区域
    private String tagArea;

    //构造方法
    public Tag(String imageID, String userID, String tagType, String tagID, String tagContent, String tagArea) {
        this.imageID = imageID;
        this.userID = userID;
        this.tagType = tagType;
        this.tagID = tagID;
        this.tagContent = tagContent;
        this.tagArea = tagArea;
    }

    //getters and setters

    public String getImageID() {
        return imageID;
    }

    public String getUserID() {
        return userID;
    }

    public String getTagType() {
        return tagType;
    }

    public String getTagID() {
        return tagID;
    }

    public String getTagContent() {
        return tagContent;
    }

    public String getTagArea() {
        return tagArea;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public void setTagArea(String tagArea) {
        this.tagArea = tagArea;
    }
}
