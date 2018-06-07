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
    //任务等级
    private TaskLevel taskLevel;
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

    //任务状态列表（开始时间，任务金额，任务要求的工人人数，发布者优惠）
    private List<PublishedTaskDetail> publishedTaskDetailList;

    //该任务的状态
    private PublishedTaskState publishedTaskState;

    public PublishedTask(TaskId taskId, UserId userId, TaskLevel taskLevel, LabelType labelType, List<Filename> imageFilenameList,
                         TaskDescription taskDescription, WorkerNum acceptedWorkerNum, WorkerNum finishedWorkerNum,
                         List<PublishedTaskDetail> publishedTaskDetailList,
                         PublishedTaskState publishedTaskState) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskLevel = taskLevel;
        this.labelType = labelType;
        this.imageFilenameList = imageFilenameList;
        this.taskDescription = taskDescription;
        this.acceptedWorkerNum = acceptedWorkerNum;
        this.finishedWorkerNum = finishedWorkerNum;
        this.publishedTaskDetailList = publishedTaskDetailList;
        this.publishedTaskState = publishedTaskState;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public UserId getUserId() {
        return userId;
    }

    public TaskLevel getTaskLevel() {
        return taskLevel;
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

    public WorkerNum getFinishedWorkerNum() {
        return finishedWorkerNum;
    }

    public Cash getTaskPrice() {
        if(publishedTaskDetailList != null){
            return publishedTaskDetailList.get(publishedTaskDetailList.size()-1).getTaskPricePerWorker();
        }else {
            return null;
        }
    }

    public List<PublishedTaskDetail> getPublishedTaskDetailList() {
        return publishedTaskDetailList;
    }

    public PublishedTaskState getPublishedTaskState() {
        return publishedTaskState;
    }
}
