package maven.data.AchievementData;

import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;

import java.util.List;

public interface AchievementDataService {
    /**
     *  获取用户的成就
     * @param userId
     * @return
     */
    List<Achievement>   getUserAchievement(UserId userId);

    /**
     * 是否可以
     * @param userId
     * @param achievementId
     * @return
     */
    boolean getAchievementCash(UserId userId, String achievementId);

    /**
     * 更新成就信息
     * @param userId
     * @return
     */

    boolean updateAchievementCash(UserId userId);




}
