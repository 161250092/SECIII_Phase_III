package maven.businessLogic.achievementBL;

import maven.model.message.Achievement;
import java.util.List;
import maven.model.primitiveType.UserId;

public interface AchievementBLService {
    /**
     * 注册用户时 初始化该用户的成就信息
     * @param userId
     * @return
     */
    boolean init_user_achievement(UserId userId);

    /**
     *  获取改用户所有的成就
     * @param userId
     * @return
     */
    List<Achievement> getUserAchievement(UserId userId);

    /**
     *领取奖励
     * @param userId
     * @param achievementId
     * @return
     */
    boolean getAchievementCash(UserId userId, int achievementId);

    /**
     *更新成就信息　
     * @param userId
     * @return
     */
    boolean updateAchievementCash(UserId userId);
}
