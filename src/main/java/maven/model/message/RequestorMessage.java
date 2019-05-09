package maven.model.message;

import maven.model.primitiveType.UserId;

import java.util.List;

public class RequestorMessage {

    private UserId userId;

    private List<PublishedTaskMessage> taskMessageList;

    private List<BillMessage> billMessageList;

    private List<AchievementMessage> achievementMessageList;

    public UserId getUserId() {
        return userId;
    }

    public List<PublishedTaskMessage> getTaskMessageList() {
        return taskMessageList;
    }

    public List<BillMessage> getBillMessageList() {
        return billMessageList;
    }

    public List<AchievementMessage> getAchievementMessageList() {
        return achievementMessageList;
    }

    public RequestorMessage(UserId userId, List<PublishedTaskMessage> taskMessageList, List<BillMessage> billMessageList, List<AchievementMessage> achievementMessageList) {
        this.userId = userId;
        this.taskMessageList = taskMessageList;
        this.billMessageList = billMessageList;
        this.achievementMessageList = achievementMessageList;
    }
}
