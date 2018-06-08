package maven.businessLogic.messageBL;

import maven.data.MessageData.MessageDataImpl;
import maven.data.MessageData.MessageDataService;
import maven.model.message.RequestorMessage;
import maven.model.message.WorkerMessage;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

public class MessageBLImpl implements MessageBLService{
    private MessageDataService messageDataService;

    public MessageBLImpl(){
        messageDataService = new MessageDataImpl();
    }

    @Override
    public RequestorMessage getAllRequestorMessage(UserId userId) {
        RequestorMessage requestorMessage = new RequestorMessage(userId, messageDataService.getUncheckedPublishedTaskMessage(userId),
                messageDataService.getUncheckedBillMessage(userId), messageDataService.getUncheckedAchievementMessage(userId));
        return requestorMessage;
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
