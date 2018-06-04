package maven.model.message;

public enum BillReason {
    //发布任务，追加任务金额，追加任务需求人数
    ASSIGN_TASK,SUPPLEMENT_TASK_CASH,SUPPLEMENT_TASK_REQUIRED_NUM,
    //完成任务并通过审核
    ACCOMPLISH_TASK
}
