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
        return new RequestorMessage(userId, messageDataService.getUncheckedPublishedTaskMessage(userId),
                messageDataService.getUncheckedBillMessage(userId), messageDataService.getUncheckedAchievementMessage(userId));

    }

    @Override
    public WorkerMessage getAllWorkerMessage(UserId userId) {
        return new WorkerMessage(userId, messageDataService.getUncheckedAcceptedTaskMessage(userId),
                messageDataService.getUncheckedGuyMessage(userId), messageDataService.getUncheckedBillMessage(userId),
                messageDataService.getUncheckedAchievementMessage(userId));
    }

    @Override
    public boolean checkRequestorTaskMessage(MessageId messageId) {
        return messageDataService.setRequestorTaskMessageChecked(messageId);
    }

    @Override
    public boolean checkWorkerTaskMessage(MessageId messageId) {
        return messageDataService.setWorkerTaskMessageChecked(messageId);
    }

    @Override
    public boolean checkGuyMessage(MessageId messageId) {
        return messageDataService.setGuyMessageChecked(messageId);
    }

    @Override
    public boolean checkBillMessage(MessageId messageId) {
        return messageDataService.setBillMessageChecked(messageId);
    }

    @Override
    public boolean checkAchievementMessage(MessageId messageId) {
        return messageDataService.setAchievementMessageChecked(messageId);
    }
}
