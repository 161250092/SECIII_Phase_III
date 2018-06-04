package maven.model.message;

import maven.model.primitiveType.*;

/**
 * 给发布者看的任务通知信息
 */
public class PublishedTaskMessage {
    //消息Id
    private MessageId messageId;
    //欲通知的发布者Id
    private UserId requestorId;
    //任务Id
    private TaskId taskId;
    //完成任务的工人Id
    private UserId workerId;
    //完成任务的工人昵称
    private Username workerName;
    //需要支付的金额
    private Cash cash;

    public MessageId getMessageId() {
        return messageId;
    }

    public UserId getRequestorId() {
        return requestorId;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public UserId getWorkerId() {
        return workerId;
    }

    public Username getWorkerName() {
        return workerName;
    }

    public Cash getCash() {
        return cash;
    }

    public PublishedTaskMessage(MessageId messageId, UserId requestorId, TaskId taskId, UserId workerId, Username workerName, Cash cash) {
        this.messageId = messageId;
        this.requestorId = requestorId;
        this.taskId = taskId;
        this.workerId = workerId;
        this.workerName = workerName;
        this.cash = cash;
    }

}
