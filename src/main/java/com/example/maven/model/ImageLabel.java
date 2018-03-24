package com.example.maven.model;

//对图片整体标注的标注类
public class ImageLabel {
    private String imageId;
    private String userId;
    private String label;

    public String getImageId() {
        return imageId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLabel() {
        return label;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
