package maven.model.message;

import maven.model.primitiveType.UserId;

import java.util.List;

public class WorkerMessage {

    private UserId userId;

    private List<PublishedTaskMessage> taskMessageList;

    private List<GuyMessage> GuyMessageList;

    private List<BillMessage> billMessageList;

    private List<AchievementMessage> achievementMessageList;

    public UserId getUserId() {
        return userId;
    }

    public List<PublishedTaskMessage> getTaskMessageList() {
        return taskMessageList;
    }

    public List<GuyMessage> getGuyMessageList() {
        return GuyMessageList;
    }

    public List<BillMessage> getBillMessageList() {
        return billMessageList;
    }

    public List<AchievementMessage> getAchievementMessageList() {
        return achievementMessageList;
    }

    public WorkerMessage(UserId userId, List<PublishedTaskMessage> taskMessageList, List<GuyMessage> guyMessageList, List<BillMessage> billMessageList, List<AchievementMessage> achievementMessageList) {
        this.userId = userId;
        this.taskMessageList = taskMessageList;
        GuyMessageList = guyMessageList;
        this.billMessageList = billMessageList;
        this.achievementMessageList = achievementMessageList;
    }
}
