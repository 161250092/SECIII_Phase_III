package maven.model.task;

import maven.model.primitiveType.UserId;

//任务质量评价类

public class AcceptedTaskQuality {
    //工人Id
    private UserId workerId;
    //发布者Id
    private UserId requestorId;
    //任务状态（任务评价） --- 通过，驳回，被发布者废弃
    private AcceptedTaskState acceptedTaskState;

    public UserId getWorkerId() {
        return workerId;
    }

    public UserId getRequestorId() {
        return requestorId;
    }

    public AcceptedTaskState getAcceptedTaskState() {
        return acceptedTaskState;
    }

    public AcceptedTaskQuality(UserId workerId, UserId requestorId, AcceptedTaskState acceptedTaskState) {
        this.workerId = workerId;
        this.requestorId = requestorId;
        this.acceptedTaskState = acceptedTaskState;
    }
}
