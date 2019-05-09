package maven.model.message;

public enum BillReason {
    //（扣除发布者金额） 发布任务，追加任务金额，追加任务需求人数
    ASSIGN_TASK,SUPPLEMENT_TASK_CASH,SUPPLEMENT_TASK_REQUIRED_NUM,
    //（退还发布者金额）终止任务
    TERMINATE_TASK,
    //（工人获取赏金）完成任务并通过审核，
    ACCOMPLISH_TASK,
    //（发布者）充值，
    CHARGE,
    //（工人）提现
    EXCHANGE,
    //领取成就奖励
    ACHIEVEMENT
}
