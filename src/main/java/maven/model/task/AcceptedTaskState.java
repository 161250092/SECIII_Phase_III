package maven.model.task;


public enum AcceptedTaskState {
    //接受，完成，通过，驳回，被发布者废弃，被工人自己废弃
    ACCEPTED, SUBMITTED, PASSED, REJECTED, ABANDONED_BY_REQUESTOR, ABANDONED_BY_WORKER;
}
