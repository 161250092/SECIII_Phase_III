package maven.model.message;

import maven.model.primitiveType.*;

public class GuyMessage {
    //消息Id
    private MessageId messageId;
    //欲通知的工人Id
    private UserId workerId;
    //欲关注工人的发布者Id
    private UserId requestorId;
    //欲关注工人的发布者昵称
    private Username requestorName;
    //工人完成该发布者的任务Id
    private TaskId taskId;
    //工人完成该发布者后获得的金额
    private Cash cash;

    public MessageId getMessageId() {
        return messageId;
    }

    public UserId getWorkerId() {
        return workerId;
    }

    public UserId getRequestorId() {
        return requestorId;
    }

    public Username getRequestorName() {
        return requestorName;
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public Cash getCash() {
        return cash;
    }

    public GuyMessage(MessageId messageId, UserId workerId, UserId requestorId, Username requestorName, TaskId taskId, Cash cash) {
        this.messageId = messageId;
        this.workerId = workerId;
        this.requestorId = requestorId;
        this.requestorName = requestorName;
        this.taskId = taskId;
        this.cash = cash;
    }
}
