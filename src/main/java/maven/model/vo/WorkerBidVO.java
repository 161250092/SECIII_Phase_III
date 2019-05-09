package maven.model.vo;

import maven.model.massTask.WorkerBid;

public class WorkerBidVO {
    //工人ID
    private String workerId;
    //工人所竞标的任务ID
    private String chosenTaskId;
    //到达时间占任务发布时间的比例
    private double ratioOfArrivedTime;
    //工人渴望获得的图片/***单价***/
    private double wantedUnitPrice;
    //工人渴望完成的最大图片数
    private int maxWantedImageNum;

    private String workerBidState;

    public WorkerBidVO(WorkerBid workerBid) {
        this.workerId = workerBid.getWorkerId().value;
        this.chosenTaskId = workerBid.getChosenTaskId().value;
        this.ratioOfArrivedTime = workerBid.getRatioOfArrivedTime();
        this.wantedUnitPrice = workerBid.getWantedUnitPrice().value;
        this.maxWantedImageNum = workerBid.getMaxWantedImageNum().value;
        this.workerBidState = workerBid.getWorkerBidState().name();
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getChosenTaskId() {
        return chosenTaskId;
    }

    public double getRatioOfArrivedTime() {
        return ratioOfArrivedTime;
    }

    public double getWantedUnitPrice() {
        return wantedUnitPrice;
    }

    public int getMaxWantedImageNum() {
        return maxWantedImageNum;
    }

    public String getWorkerBidState() {
        return workerBidState;
    }
}
