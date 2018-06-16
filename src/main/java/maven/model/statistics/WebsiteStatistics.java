package maven.model.statistics;

/**
 * 站点统计信息类
 */
public class WebsiteStatistics {
    private int numOfUsers;//用户数
    private int numOfTasks;//任务数
    private int numOfIncompleteTasks;//进行中任务的数目
    private int numOfAccomplishedTask;//已完成任务的数目
    private double chargedCash;//用户充值的总金额
    private double exchangedCash;//用户兑换的总金额

    public WebsiteStatistics(int numOfUsers, int numOfTasks, int numOfIncompleteTasks, int numOfAccomplishedTask,
                             double chargedCash, double exchangedCash){
        this.numOfUsers = numOfUsers;
        this.numOfTasks = numOfTasks;
        this.numOfIncompleteTasks = numOfIncompleteTasks;
        this.numOfAccomplishedTask = numOfAccomplishedTask;
        this.chargedCash = chargedCash;
        this.exchangedCash = exchangedCash;
    }

    public int getNumOfUsers() {
        return numOfUsers;
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
