package model.task;

import model.label.Label;
import model.primitiveType.*;

import java.util.Date;

/**
 * 工人接受的任务
 */
public class AcceptedTask {
    //用户Id
    UserId userId;
    //任务Id
    TaskId taskId;
    //创建时间
    Date startTime;

    //该用户对该任务的所有标注信息
    Label taskImageLabelList[];

    //该用户接任务时，任务的价格
    Cash taskPrice;
    //该用户接任务时所享受的优惠
    WorkerDiscount workerDiscount;

    //该任务的状态
    AcceptedTaskState acceptedTaskState;

    public AcceptedTask(UserId userId, TaskId taskId, Label taskImageLabelList[], Cash taskPrice, WorkerDiscount workerDiscount){
        this.userId = userId;
        this.taskId = taskId;
        this.startTime = new Date();
        this.taskImageLabelList = taskImageLabelList;
        this.taskPrice = taskPrice;
        this.workerDiscount = workerDiscount;
        this.acceptedTaskState = AcceptedTaskState.ACCEPTED;
    }

    public UserId getUserId() {
        return userId;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Label[] getTaskImageLabelList() {
        return taskImageLabelList;
    }

    public Cash getOriginalTaskPrice() {
        return taskPrice;
    }

    /**
     * 获取打折后的任务价格
     * @return 打折后的任务价格
     */
    public Cash getActualTaskPrice(){
        //***********************************
        //未完成
        //***********************************
        return new Cash(-1);
    }

    public WorkerDiscount getWorkerDiscount() {
        return workerDiscount;
    }

    public AcceptedTaskState getAcceptedTaskState() {
        return acceptedTaskState;
    }

}
