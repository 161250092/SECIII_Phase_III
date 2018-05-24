package model.vo;

/**
 * 站点统计信息类
 */
public class WebsiteStatisticsVO {
    //用户数
    private int numOfUsers;
    //任务数
    private int numOfTasks;
    //进行中任务的数目
    private int numOfIncompleteTasks;
    //已完成任务的数目
    private int numOfAccomplishedTask;

    public WebsiteStatisticsVO(int numOfUsers, int numOfTasks, int numOfIncompleteTasks, int numOfAccomplishedTask){
        this.numOfUsers = numOfUsers;
        this.numOfTasks = numOfTasks;
        this.numOfIncompleteTasks = numOfIncompleteTasks;
        this.numOfAccomplishedTask = numOfAccomplishedTask;
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
}