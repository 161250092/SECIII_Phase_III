package maven.model.statistics;

import java.util.List;

/**
 * 网站流量统计类
 * 在某个时间区间内的统计信息
 */
public class WebsiteTrafficStatics {
    //发布者发布的任务数量 列表
    private List<Integer> numOfPublishedTask;
    //工人提交的完成任务数量 列表
    private List<Integer> numOfSubmittedAcceptedTask;
    //用户充值的总金额
    private double chargedCash;
    //用户兑换的总金额
    private double exchangedCash;

    public List<Integer> getNumOfPublishedTask() {
        return numOfPublishedTask;
    }

    public List<Integer> getNumOfSubmittedAcceptedTask() {
        return numOfSubmittedAcceptedTask;
    }

    public double getChargedCash() {
        return chargedCash;
    }

    public double getExchangedCash() {
        return exchangedCash;
    }

    public WebsiteTrafficStatics(List<Integer> numOfPublishedTask, List<Integer> numOfSubmittedAcceptedTask, double chargedCash, double exchangedCash) {
        this.numOfPublishedTask = numOfPublishedTask;
        this.numOfSubmittedAcceptedTask = numOfSubmittedAcceptedTask;
        this.chargedCash = chargedCash;
        this.exchangedCash = exchangedCash;
    }
}
