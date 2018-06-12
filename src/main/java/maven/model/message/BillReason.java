package maven.model.message;

public enum BillReason {
    //（扣除发布者金额） 发布任务，追加任务金额，追加任务需求人数
    ASSIGN_TASK,SUPPLEMENT_TASK_CASH,SUPPLEMENT_TASK_REQUIRED_NUM,
    //（退还发布者金额）终止任务
    TERMINATE_TASK,
    //（工人获取赏金）完成任务并通过审核，
    ACCOMPLISH_TASK
}
