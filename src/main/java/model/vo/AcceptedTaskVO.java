package model.vo;

import model.primitiveType.*;
import model.task.AcceptedTaskState;

public class AcceptedTaskVO {
    //任务Id
    TaskId taskId;

    //工人Id
    UserId userId;

    //工人名称
    Username username;

    //标注类型
    LabelType labelType;

    //任务描述
    TaskDescription taskDescription;

    //完成该任务可获取的钱数
    Cash taskPrice;

    //该任务的状态
    AcceptedTaskState acceptedTaskState;

    //系统对工人标注的评分
    int score;

    public TaskId getTaskId() {
        return taskId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Username getUsername() {
        return username;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public Cash getTaskPrice() {
        return taskPrice;
    }

    public AcceptedTaskState getAcceptedTaskState() {
        return acceptedTaskState;
    }

    public int getScore() {
        return score;
    }

    public AcceptedTaskVO(TaskId taskId, UserId userId, Username username, LabelType labelType, TaskDescription taskDescription, Cash taskPrice, AcceptedTaskState acceptedTaskState, int score) {
        this.taskId = taskId;
        this.userId = userId;
        this.username = username;
        this.labelType = labelType;
        this.taskDescription = taskDescription;
        this.taskPrice = taskPrice;
        this.acceptedTaskState = acceptedTaskState;
        this.score = score;
    }
}
