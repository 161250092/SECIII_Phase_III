package maven.model.task;

import maven.model.primitiveType.*;

import java.util.Date;

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

    //该用户接任务时，任务的价格
    private Cash taskPrice;
    //该用户接任务时所享受的优惠
    private WorkerDiscount workerDiscount;

    //该任务的状态
    private AcceptedTaskState acceptedTaskState;
    //系统对工人标注的评分
    private LabelScore labelScore;

    public AcceptedTask(UserId userId, TaskId taskId, Date startTime, Cash taskPrice, WorkerDiscount workerDiscount, AcceptedTaskState acceptedTaskState, LabelScore labelScore) {
        this.userId = userId;
        this.taskId = taskId;
        this.startTime = startTime;
        this.taskPrice = taskPrice;
        this.workerDiscount = workerDiscount;
        this.acceptedTaskState = acceptedTaskState;
        this.labelScore = labelScore;
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

    public Cash getOriginalTaskPrice() {
        return taskPrice;
    }

    public LabelScore getLabelScore() {
        return labelScore;
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
