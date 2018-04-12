package com.example.maven.model.po;

/**
 * 众包标注任务类
 */
public class Task {
    //任务Id  用户昵称_标注类型_创建时间
    String taskId;
    //标注类型
    String labelType;
    //图片信息数组
    String[] imageInformation;
    //任务描述
    String description;
    //该任务要求的标注人次
    int requiredNumber;
    //完成该任务要求的标注人次
    int finishedNumber;
    //完成该任务可获取的积分值
    int score;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public void setImageInformation(String[] imageInformation) {
        this.imageInformation = imageInformation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredNumber(int requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public void setFinishedNumber(int finishedNumber) {
        this.finishedNumber = finishedNumber;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getLabelType() {
        return labelType;
    }

    public String[] getImageInformation() {
        return imageInformation;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredNumber() {
        return requiredNumber;
    }

    public int getFinishedNumber() {
        return finishedNumber;
    }

    public int getScore() {
        return score;
    }

    public Task(String taskId, String labelType, String[] imageInformation, String description, int requiredNumber, int finishedNumber, int score) {
        this.taskId = taskId;
        this.labelType = labelType;
        this.imageInformation = imageInformation;
        this.description = description;
        this.requiredNumber = requiredNumber;
        this.finishedNumber = finishedNumber;
        this.score = score;
    }
}
