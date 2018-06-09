package maven.model.message;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;

public class Achievement {
    //userID
    private UserId userId;
    //成就ID
    private  String achievementId;
   //成就名称
    private  String achievementName;
    //成就描述
    private  String  description;
    //进度
    private  double  process;
    //是否完成
    private  boolean  isFinished;
    //奖励是否领取
    private  boolean  isRewardGet;
    //成就奖励
    private Cash cash;

    public Achievement(UserId userId, String achievementId, String achievementName, String description, double process, boolean isFinished, boolean isRewardGet, Cash cash) {
        this.userId = userId;
        this.achievementId = achievementId;
        this.achievementName = achievementName;
        this.description = description;
        this.process = process;
        this.isFinished = isFinished;
        this.isRewardGet = isRewardGet;
        this.cash = cash;
    }


    public UserId getUserId() {
        return userId;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public String getDescription() {
        return description;
    }

    public double getProcess() {
        return process;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isRewardGet() {
        return isRewardGet;
    }

    public Cash getCash() {
        return cash;
    }
}
