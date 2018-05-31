package maven.model.task;

import maven.model.primitiveType.*;

import java.util.List;

/**
 * 发布者发布的任务
 */
public class PublishedTask {
    //任务Id
    private TaskId taskId;
    //发布者Id
    private UserId userId;
    //标注类型
    private LabelType labelType;

    //图片文件名数组
    private List<Filename> imageFilenameList;
    //任务描述
    private TaskDescription taskDescription;

    //接受该任务的工人人数
    private WorkerNum acceptedWorkerNum;
    //标注已通过审核的工人人数
    private WorkerNum finishedWorkerNum;

    //发布者提供的标注样本信息
    private Sample sample;

    //完成该任务可获取的钱数
    private Cash taskPrice;

    //任务状态列表（开始时间，任务金额，任务要求的工人人数，发布者优惠）
    private List<PublishedTaskDetail> publishedTaskDetailList;

    //该任务的状态
    private PublishedTaskState publishedTaskState;

    public PublishedTask(TaskId taskId, UserId userId, LabelType labelType, List<Filename> imageFilenameList,
                         TaskDescription taskDescription, WorkerNum acceptedWorkerNum, WorkerNum finishedWorkerNum, Sample sample,
                         Cash taskPrice, List<PublishedTaskDetail> publishedTaskDetailList,
                         PublishedTaskState publishedTaskState) {
        this.taskId = taskId;
        this.userId = userId;
        this.labelType = labelType;
        this.imageFilenameList = imageFilenameList;
        this.taskDescription = taskDescription;
        this.acceptedWorkerNum = acceptedWorkerNum;
        this.finishedWorkerNum = finishedWorkerNum;
        this.sample = sample;
        this.taskPrice = taskPrice;
        this.publishedTaskDetailList = publishedTaskDetailList;
        this.publishedTaskState = publishedTaskState;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public UserId getUserId() {
        return userId;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public List<Filename> getImageFilenameList() {
        return imageFilenameList;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public WorkerNum getRequiredWorkerNum() {
        if(publishedTaskDetailList != null){
            return publishedTaskDetailList.get(publishedTaskDetailList.size()-1).getRequiredWorkerNum();
        }else {
            return null;
        }
    }

    public WorkerNum getAcceptedWorkerNum() {
        return acceptedWorkerNum;
    }

    public Sample getSample() {
        return sample;
    }

    public WorkerNum getFinishedWorkerNum() {
        return finishedWorkerNum;
    }

    public Cash getTaskPrice() {
        return taskPrice;
    }

    public List<PublishedTaskDetail> getPublishedTaskDetailList() {
        return publishedTaskDetailList;
    }

    public PublishedTaskState getPublishedTaskState() {
        return publishedTaskState;
    }
}
