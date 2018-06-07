package maven.businessLogic.achievementBL;

import maven.model.message.Achievement;
import java.util.List;
import maven.model.primitiveType.UserId;

public interface AchievementBLService {

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

        boolean   getAchievementCash(UserId userId,String achievementId);

    /**
     * 更新成就信息
     * @param userId
     * @return
     */
        boolean    updateAchievementCash(UserId userId);

}
