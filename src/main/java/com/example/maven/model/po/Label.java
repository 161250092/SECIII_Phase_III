package com.example.maven.model.po;

import java.io.Serializable;

/**
 * 所有标签类的父类
 */
public class Label implements Serializable{
    //任务Id
    private String taskId;
    //图片Id
    private String imageId;
    //用户Id
    private String userId;
    //标注类型
    private String type;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTaskId() {
        return taskId;
    }
    public String getImageId() {
        return imageId;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }


    /**
     * 父类构造方法
     * @param taskId 任务Id
     * @param imageId 图片Id
     * @param userId 用户Id
     */
    public Label(String taskId, String imageId, String userId) {
        this.taskId = taskId;
        this.imageId = imageId;
        this.userId = userId;
    }

    public Label(){
    }
}
