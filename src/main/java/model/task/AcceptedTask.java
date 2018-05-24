package model.task;

import model.label.Label;
import model.primitiveType.*;

import java.util.Date;
import java.util.List;

/**
 * 工人接受的任务
 */
public class AcceptedTask {
    //用户Id
    private UserId userId;
    //任务Id
    private TaskId taskId;
    //创建时间
    private Date startTime;

    //该用户对该任务的所有标注信息
    private List<Label> taskLabelList;

    //该用户接任务时，任务的价格
    private Cash taskPrice;
    //该用户接任务时所享受的优惠
    private WorkerDiscount workerDiscount;

    //该任务的状态
    private AcceptedTaskState acceptedTaskState;

    public AcceptedTask(UserId userId, TaskId taskId, List<Label> taskLabelList, Cash taskPrice, WorkerDiscount workerDiscount){
        this.userId = userId;
        this.taskId = taskId;
        this.startTime = new Date();
        this.taskLabelList = taskLabelList;
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

    public List<Label> getTaskLabelList() {
        return taskLabelList;
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
