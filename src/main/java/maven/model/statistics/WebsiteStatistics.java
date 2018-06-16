package maven.model.statistics;

/**
 * 站点统计信息类
 */
public class WebsiteStatistics {
    //发布者总数
    private int numOfRequestors;
    //工人总数
    private int numOfWorkers;
    //任务总数
    private int numOfTasks;
    //进行中任务的数目
    private int numOfIncompleteTasks;
    //已完成任务的数目
    private int numOfAccomplishedTask;
    //用户充值的总金额
    private double chargedCash;
    //用户兑换的总金额
    private double exchangedCash;

    public WebsiteStatistics(int numOfRequestors, int numOfWorkers, int numOfTasks, int numOfIncompleteTasks, int numOfAccomplishedTask, double chargedCash, double exchangedCash) {
        this.numOfRequestors = numOfRequestors;
        this.numOfWorkers = numOfWorkers;
        this.numOfTasks = numOfTasks;
        this.numOfIncompleteTasks = numOfIncompleteTasks;
        this.numOfAccomplishedTask = numOfAccomplishedTask;
        this.chargedCash = chargedCash;
        this.exchangedCash = exchangedCash;
    }

    public int getNumOfRequestors() {
        return numOfRequestors;
    }

    public int getNumOfWorkers() {
        return numOfWorkers;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public int getNumOfIncompleteTasks() {
        return numOfIncompleteTasks;
    }

    public int getNumOfAccomplishedTask() {
        return numOfAccomplishedTask;
    }

    public double getChargedCash() {
        return chargedCash;
    }

    public double getExchangedCash() {
        return exchangedCash;
    }

}
