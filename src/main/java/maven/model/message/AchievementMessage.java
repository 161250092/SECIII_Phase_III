package maven.model.message;

import maven.model.primitiveType.MessageId;
import maven.model.primitiveType.UserId;

public class AchievementMessage {
    //消息Id
    private MessageId messageId;
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


    public MessageId getMessageId() {
        return messageId;
    }

    public AchievementMessage(MessageId messageId, UserId userId, Achievement achievement) {
        this.messageId = messageId;
        this.userId = userId;
        this.achievement = achievement;
    }
}
