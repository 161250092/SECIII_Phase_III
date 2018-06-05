package maven.model.message;

import maven.model.primitiveType.*;
import maven.model.task.AcceptedTaskState;

/**
 * 给工人看的任务通知信息
 */
public class AcceptedTaskMessage {
    //消息Id
    private MessageId messageId;
    //欲通知的工人Id
    private UserId workerId;
    //任务Id
    private TaskId taskId;
    //工人可获得的金额
    private Cash cash;
    //审批后的任务状态
    private AcceptedTaskState acceptedTaskState;
    //是否被用户确认查看过
    private boolean isConfirmed;

    public UserId getWorkerId() {
        return workerId;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public Cash getCash() {
        return cash;
    }

    public AcceptedTaskState getAcceptedTaskState() {
        return acceptedTaskState;
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public AcceptedTaskMessage(MessageId messageId, UserId workerId, TaskId taskId, Cash cash, AcceptedTaskState acceptedTaskState) {
        this.messageId = messageId;
        this.workerId = workerId;
        this.taskId = taskId;
        this.cash = cash;
        this.acceptedTaskState = acceptedTaskState;
        this.isConfirmed = false;
    }
}
