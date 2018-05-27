package maven.model.vo;

import maven.model.primitiveType.*;
import maven.model.task.AcceptedTask;

import java.io.Serializable;

public class AcceptedTaskVO implements Serializable{
    //任务Id
    private String taskId;
    //工人Id
    private String userId;
    //工人名称
    private String username;

    //标注类型
    private String labelType;
    //任务描述
    private String taskDescription;
    //该任务的状态
    private String acceptedTaskState;

    //完成该任务可获取的钱数
    private double taskPrice;
    //系统对工人标注的评分
    private double labelScore;

    private AcceptedTaskVO(String taskId, String userId, String username, String labelType,
                          String taskDescription, double taskPrice, String acceptedTaskState, double labelScore) {
        this.taskId = taskId;
        this.userId = userId;
        this.username = username;
        this.labelType = labelType;
        this.taskDescription = taskDescription;
        this.taskPrice = taskPrice;
        this.acceptedTaskState = acceptedTaskState;
        this.labelScore = labelScore;
    }

    public AcceptedTaskVO(AcceptedTask acceptedTask, Username username, LabelType labelType, TaskDescription description, LabelScore labelScore){
        this(acceptedTask.getTaskId().value, acceptedTask.getUserId().value, username.value, labelType.value,
                description.value, acceptedTask.getActualTaskPrice().value, acceptedTask.getAcceptedTaskState().toString(), labelScore.value);
    }

    public String getTaskId() {
        return taskId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getLabelType() {
        return labelType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public double getTaskPrice() {
        return taskPrice;
    }

    public String getAcceptedTaskState() {
        return acceptedTaskState;
    }

    public double getLabelScore() {
        return labelScore;
    }
}
