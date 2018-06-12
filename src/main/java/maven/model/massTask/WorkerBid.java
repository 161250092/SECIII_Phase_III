package maven.model.massTask;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;

import java.awt.*;

/**
 * 工人对某一个任务的竞价
 */
public class WorkerBid {
    //工人ID
    private UserId workerId;
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

    public WorkerBid(UserId workerId, double ratioOfArrivedTime, Cash wantedPrice, ImageNum maxWantedImageNum) {
        this.workerId = workerId;
        this.ratioOfArrivedTime = ratioOfArrivedTime;
        this.wantedUnitPrice = wantedPrice;
        this.maxWantedImageNum = maxWantedImageNum;
    }

    public UserId getWorkerId() {
        return workerId;
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
}
