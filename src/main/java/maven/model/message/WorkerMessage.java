package maven.model.message;

import maven.model.primitiveType.UserId;

import java.util.List;

public class WorkerMessage {

    private UserId userId;

    private List<AcceptedTaskMessage> taskMessageList;

    private List<GuyMessage> guyMessageList;

    private List<BillMessage> billMessageList;

    private List<AchievementMessage> achievementMessageList;

    public UserId getUserId() {
        return userId;
    }

    public List<AcceptedTaskMessage> getTaskMessageList() {
        return taskMessageList;
    }

    public List<GuyMessage> getGuyMessageList() {
        return guyMessageList;
    }

    public List<BillMessage> getBillMessageList() {
        return billMessageList;
    }

    public List<AchievementMessage> getAchievementMessageList() {
        return achievementMessageList;
    }

    public WorkerMessage(UserId userId, List<AcceptedTaskMessage> taskMessageList, List<GuyMessage> guyMessageList, List<BillMessage> billMessageList, List<AchievementMessage> achievementMessageList) {
        this.userId = userId;
        this.taskMessageList = taskMessageList;
        this.guyMessageList = guyMessageList;
        this.billMessageList = billMessageList;
        this.achievementMessageList = achievementMessageList;
    }
}
