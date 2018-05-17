package com.example.maven.model.task;

import com.example.maven.model.primitiveType.*;

import java.util.ArrayList;

/**
 * 发布者发布的任务
 */
public class PublishedTask {
    //任务Id
    TaskId taskId;
    //标注类型
    LabelType labelType;

    //图片文件名数组
    Filename[] imageFilename;
    //任务描述
    TaskDescription taskDescription;

    //接受该任务的工人人数
    WorkerNum acceptedWorkerNum;
    //标注已通过审核的工人人数
    WorkerNum finishedWorkerNum;

    //完成该任务可获取的钱数
    Cash taskPrice;

    //任务状态列表（开始时间，任务金额，任务要求的工人人数，发布者优惠）
    ArrayList<TaskState> taskStateList;

    public PublishedTask(TaskId taskId, LabelType labelType, Filename[] imageFilename, TaskDescription taskDescription,
                         WorkerNum acceptedWorkerNum, WorkerNum finishedWorkerNum,
                         Cash taskPrice, ArrayList<TaskState> taskStateList) {
        this.taskId = taskId;
        this.labelType = labelType;
        this.imageFilename = imageFilename;
        this.taskDescription = taskDescription;
        this.acceptedWorkerNum = acceptedWorkerNum;
        this.finishedWorkerNum = finishedWorkerNum;
        this.taskPrice = taskPrice;
        this.taskStateList = taskStateList;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public Filename[] getImageFilename() {
        return imageFilename;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public WorkerNum getRequiredWorkerNum() {
        if(taskStateList != null){
            return taskStateList.get(taskStateList.size()-1).requiredWorkerNum;
        }else {
            return null;
        }
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

    public ArrayList<TaskState> getTaskStateList() {
        return taskStateList;
    }
}
