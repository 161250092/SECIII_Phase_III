package maven.model.vo;

import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskState;

import java.io.Serializable;

public class PublishedTaskVO implements Serializable {
    //任务Id
    private String taskId;

    //任务等级
    private String taskLevel;

    //标注类型
    private String labelType;

    //任务描述
    private String taskDescription;

    //接受该任务的工人人数
    private int acceptedWorkerNum;

    //标注已通过审核的工人人数
    private int finishedWorkerNum;

    //任务所需的工人人数
    private int requiredWorkerNum;

    //该任务包含的图片数量
    private int imageNum;

    //完成该任务可获取的钱数
    private double taskPrice;

    //该任务的状态
    private PublishedTaskState publishedTaskState;

    public String getTaskId() {
        return taskId;
    }

    public String getTaskLevel() {
        return taskLevel;
    }

    public int getImageNum() {
        return imageNum;
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

    public int getRequiredWorkerNum() {
        return requiredWorkerNum;
    }

    public double getTaskPrice() {
        return taskPrice;
    }

    public PublishedTaskState getPublishedTaskState() {
        return publishedTaskState;
    }


    private PublishedTaskVO(String taskId, String taskLevel, String labelType, String taskDescription, int acceptedWorkerNum, int finishedWorkerNum, int requiredWorkerNum, int imageNum, double taskPrice, PublishedTaskState publishedTaskState) {
        this.taskId = taskId;
        this.taskLevel = taskLevel;
        this.labelType = labelType;
        this.taskDescription = taskDescription;
        this.acceptedWorkerNum = acceptedWorkerNum;
        this.finishedWorkerNum = finishedWorkerNum;
        this.requiredWorkerNum = requiredWorkerNum;
        this.imageNum = imageNum;
        this.taskPrice = taskPrice;
        this.publishedTaskState = publishedTaskState;
    }





    /**
     * 提供从 PublishedTask类 实例 到 PublishedTaskVO的转换方法
     * @param publishedTask PublishedTask类
     */
    public PublishedTaskVO(PublishedTask publishedTask){
        this(publishedTask.getTaskId().value, publishedTask.getLabelType().value, publishedTask.getTaskDescription().value,
                publishedTask.getAcceptedWorkerNum().value, publishedTask.getFinishedWorkerNum().value,
                publishedTask.getRequiredWorkerNum().value, publishedTask.getImageFilenameList().size(),
                publishedTask.getTaskPrice().value, publishedTask.getPublishedTaskState());
    }

}
