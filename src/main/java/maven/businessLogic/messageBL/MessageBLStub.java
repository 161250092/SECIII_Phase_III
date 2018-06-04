package maven.businessLogic.messageBL;

import maven.model.message.RequestorMessage;
import maven.model.message.WorkerMessage;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

public class MessageBLStub implements MessageBLService {
    @Override
    public RequestorMessage getAllRequestorMessage(UserId userId) {
        return null;
    }

    @Override
    public WorkerMessage getAllWorkerMessage(UserId userId) {
        return null;
    }

    @Override
    public boolean confirmRequestorTaskMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean confirmWorkerTaskMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean confirmGuyMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean confirmBillMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean confirmAchievementMessage(MessageId messageId) {
        return false;
    }
}
