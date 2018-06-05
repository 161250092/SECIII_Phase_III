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
    public boolean checkRequestorTaskMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean checkWorkerTaskMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean checkGuyMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean checkBillMessage(MessageId messageId) {
        return false;
    }

    @Override
    public boolean checkAchievementMessage(MessageId messageId) {
        return false;
    }
}
