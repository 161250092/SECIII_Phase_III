package model.task;

import model.primitiveType.Cash;
import model.primitiveType.WorkerNum;

import java.util.Date;

/**
 * 任务瞬时状态
 */
public class PublishedTaskState {
    //创建时间
    Date startTime;
    //任务所需的工人人数
    WorkerNum requiredWorkerNum;
    //任务在这一状态下，每个工人完成该任务后获得的金额
    Cash taskPricePerWorker;
    //任务这一状态下所使用的发布者优惠
    RequestorDiscount requestorDiscount;

    public PublishedTaskState(WorkerNum requiredWorkerNum, Cash taskPricePerWorker, RequestorDiscount requestorDiscount) {
        this.startTime = new Date();
        this.requiredWorkerNum = requiredWorkerNum;
        this.taskPricePerWorker = taskPricePerWorker;
        this.requestorDiscount = requestorDiscount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public WorkerNum getRequiredWorkerNum() {
        return requiredWorkerNum;
    }

    public Cash getTaskPricePerWorker() {
        return taskPricePerWorker;
    }

    public RequestorDiscount getRequestorDiscount() {
        return requestorDiscount;
    }
}
