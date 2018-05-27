package maven.model.vo;

import maven.model.task.PublishedTask;

import java.util.ArrayList;

public class PublishedTaskVO {
    //任务Id
    private String taskId;

    //标注类型
    private String labelType;

    //任务描述
    private String taskDescription;

    //接受该任务的工人人数
    private int acceptedWorkerNum;

    //标注已通过审核的工人人数
    private int finishedWorkerNum;

    //完成该任务可获取的钱数
    private double taskPrice;

    public String getTaskId() {
        return taskId;
    }

    public String getLabelType() {
        return labelType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getAcceptedWorkerNum() {
        return acceptedWorkerNum;
    }

    public int getFinishedWorkerNum() {
        return finishedWorkerNum;
    }

    public double getTaskPrice() {
        return taskPrice;
    }

    private PublishedTaskVO(String taskId, String labelType, String taskDescription, int acceptedWorkerNum, int finishedWorkerNum, double taskPrice) {
        this.taskId = taskId;
        this.labelType = labelType;
        this.taskDescription = taskDescription;
        this.acceptedWorkerNum = acceptedWorkerNum;
        this.finishedWorkerNum = finishedWorkerNum;
        this.taskPrice = taskPrice;
    }

    /**
     * 提供从 PublishedTask类 实例 到 PublishedTaskVO的转换方法
     * @param publishedTask PublishedTask类
     */
    public PublishedTaskVO(PublishedTask publishedTask){
        this(publishedTask.getTaskId().value, publishedTask.getLabelType().value, publishedTask.getTaskDescription().value,
                publishedTask.getAcceptedWorkerNum().value, publishedTask.getFinishedWorkerNum().value, publishedTask.getTaskPrice().value);
    }

}
