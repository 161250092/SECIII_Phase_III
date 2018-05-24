package model.vo;

import model.primitiveType.*;
import model.task.PublishedTaskState;

import java.util.ArrayList;

public class PublishedTaskVO {
    //任务Id
    TaskId taskId;

    //标注类型
    LabelType labelType;

    //任务描述
    TaskDescription taskDescription;

    //接受该任务的工人人数
    WorkerNum acceptedWorkerNum;

    //标注已通过审核的工人人数
    WorkerNum finishedWorkerNum;

    //完成该任务可获取的钱数
    Cash taskPrice;

    public TaskId getTaskId() {
        return taskId;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public WorkerNum getAcceptedWorkerNum() {
        return acceptedWorkerNum;
    }

    public WorkerNum getFinishedWorkerNum() {
        return finishedWorkerNum;
    }

    public Cash getTaskPrice() {
        return taskPrice;
    }

    public PublishedTaskVO(TaskId taskId, LabelType labelType, TaskDescription taskDescription, WorkerNum acceptedWorkerNum, WorkerNum finishedWorkerNum, Cash taskPrice) {
        this.taskId = taskId;
        this.labelType = labelType;
        this.taskDescription = taskDescription;
        this.acceptedWorkerNum = acceptedWorkerNum;
        this.finishedWorkerNum = finishedWorkerNum;
        this.taskPrice = taskPrice;
    }
}
