package maven.data.MessageData;

import maven.model.message.*;
import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

import java.util.List;

public class MessageDataImpl implements  MessageDataService{

    @Override
    public boolean savePublishedTaskMessage(PublishedTaskMessage publishedTaskMessage) {
        return false;
    }

    @Override
    public boolean saveAcceptedTaskMessage(AcceptedTaskMessage acceptedTaskMessage) {
        return false;
    }

    @Override
    public boolean saveGuyMessage(GuyMessage guyMessage) {
        return false;
    }

    @Override
    public boolean saveBillMessage(BillMessage billMessage) {
        return false;
    }

    @Override
    public boolean saveAchievementMessage(AchievementMessage achievementMessage) {
        return false;
    }

    @Override
    public List<PublishedTaskMessage> getUncheckedPublishedTaskMessage(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTaskMessage> getUncheckedAcceptedTaskMessage(UserId userId) {
        return null;
    }

    @Override
    public List<GuyMessage> getUncheckedGuyMessage(UserId userId) {
        return null;
    }

    @Override
    public List<BillMessage> getUncheckedBillMessage(UserId userId) {
        return null;
    }

    @Override
    public List<AchievementMessage> getUncheckedAchievementMessage(UserId userId) {
        return null;
    }

    @Override
    public boolean setRequestorTaskMessageChecked(MessageId messageId) {
        return false;
    }

    @Override
    public boolean setWorkerTaskMessageChecked(MessageId messageId) {
        return false;
    }

    @Override
    public boolean setGuyMessageChecked(MessageId messageId) {
        return false;
    }

    @Override
    public boolean setBillMessageChecked(MessageId messageId) {
        return false;
    }

    @Override
    public boolean setAchievementMessageChecked(MessageId messageId) {
        return false;
    }
}
