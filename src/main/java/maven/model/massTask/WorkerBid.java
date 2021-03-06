package maven.model.massTask;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.vo.WorkerBidVO;

import java.awt.*;

/**
 * 工人对某一个任务的竞价
 */
public class WorkerBid {
    //工人ID
    private UserId workerId;
    //工人所竞标的任务ID
    private TaskId chosenTaskId;
    /**
     * ratioOfArrivedTime 为 到达时间占任务发布时间的比例
     * 其值 ∈ (0 , 1]
     * 例如：A任务从发布到截止经过了4天
     *       工人x是在第3天申请该任务的
     *       则这个比例为 3/4 = 0.75
     *
     * 再比如说： B任务从发布到截止经过了10天
     *           工人y是在第2天申请该任务的
     *           则这个比例为 2/10 = 0.2
     */
    private double ratioOfArrivedTime;
    //工人渴望获得的图片/***单价***/
    private Cash wantedUnitPrice;
    //工人渴望完成的最大图片数
    private ImageNum maxWantedImageNum;
    //该竞标的状态 {等待，成功，失败}
    private WorkerBidState workerBidState;
    //若竞标成功，该工人所分到的第一张图片在任务图片列表的下标
    private int fileListStartIndex;
    //若竞标成功，该工人所分到的图片数
    private int fileListLength;

    public WorkerBid(UserId workerId, TaskId chosenTaskId, double ratioOfArrivedTime, Cash wantedUnitPrice, ImageNum maxWantedImageNum, WorkerBidState workerBidState) {
        this.workerId = workerId;
        this.chosenTaskId = chosenTaskId;
        this.ratioOfArrivedTime = ratioOfArrivedTime;
        this.wantedUnitPrice = wantedUnitPrice;
        this.maxWantedImageNum = maxWantedImageNum;
        this.workerBidState = workerBidState;
        this.fileListStartIndex = -1;
        this.fileListLength = -1;
    }

    public WorkerBid(UserId workerId, TaskId chosenTaskId, double ratioOfArrivedTime, Cash wantedUnitPrice, ImageNum maxWantedImageNum, WorkerBidState workerBidState,
                     int fileListStartIndex, int fileListLength) {
        this.workerId = workerId;
        this.chosenTaskId = chosenTaskId;
        this.ratioOfArrivedTime = ratioOfArrivedTime;
        this.wantedUnitPrice = wantedUnitPrice;
        this.maxWantedImageNum = maxWantedImageNum;
        this.workerBidState = workerBidState;
        this.fileListStartIndex = fileListStartIndex;
        this.fileListLength = fileListLength;
    }
    public WorkerBid(WorkerBidVO bidVO) {
        this(new UserId(bidVO.getWorkerId()), new TaskId(bidVO.getChosenTaskId()), bidVO.getRatioOfArrivedTime(),
                new Cash(bidVO.getWantedUnitPrice()), new ImageNum(bidVO.getMaxWantedImageNum()), WorkerBidState.valueOf(bidVO.getWorkerBidState()));
    }

    public UserId getWorkerId() {
        return workerId;
    }

    public TaskId getChosenTaskId() {
        return chosenTaskId;
    }

    public double getRatioOfArrivedTime() {
        return ratioOfArrivedTime;
    }

    public Cash getWantedUnitPrice() {
        return wantedUnitPrice;
    }

    public ImageNum getMaxWantedImageNum() {
        return maxWantedImageNum;
    }

    public WorkerBidState getWorkerBidState() {
        return workerBidState;
    }

    public int getFileListStartIndex() {
        return fileListStartIndex;
    }

    public void setFileListStartIndex(int fileListStartIndex) {
        this.fileListStartIndex = fileListStartIndex;
    }

    public int getFileListLength() {
        return fileListLength;
    }

    public void setFileListLength(int fileListLength) {
        this.fileListLength = fileListLength;
    }
}
