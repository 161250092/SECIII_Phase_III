package maven.model.message;

import maven.model.primitiveType.UserId;

public class AchievementMessage {
    //欲通知的用户Id
    private UserId userId;
    //达成的成就
    private Achievement achievement;

    public UserId getUserId() {
        return userId;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public AchievementMessage(UserId userId, Achievement achievement) {
        this.userId = userId;
        this.achievement = achievement;
    }
}
