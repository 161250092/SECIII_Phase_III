package com.example.maven.model.vo;

public class TaskVO {
    //任务Id
    String taskId;
    //标注类型
    String labelType;
    //任务描述
    String description;
    //该任务要求的标注人次
    int requiredNumber;
    //完成该任务要求的标注人次
    int finishedNumber;
    //完成该任务可获取的积分值
    int score;

    public TaskVO(String taskId, String labelType, String description, int requiredNumber, int finishedNumber, int score) {
        this.taskId = taskId;
        this.labelType = labelType;
        this.description = description;
        this.requiredNumber = requiredNumber;
        this.finishedNumber = finishedNumber;
        this.score = score;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getLabelType() {
        return labelType;
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

}
