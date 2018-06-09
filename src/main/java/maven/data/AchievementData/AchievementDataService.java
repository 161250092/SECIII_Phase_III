package maven.data.AchievementData;

import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;

import java.util.ArrayList;
import java.util.List;

public interface AchievementDataService {
    /**
     * 将Achievement的LIST插入数据库
     * @param list
     * @return
     */


    boolean init_user_achievement(ArrayList<Achievement> list);


    /**
     *  获取用户的成就
     * @param userId
     * @return
     */
    List<Achievement>   getUserAchievement(UserId userId);

    /**
     * 根据USERID ,ACHIEVEMENTID 找到对应的成就信息，
     * 查找其中的isFinished的值，如果isFinished 是TRUE,则返回TRUE,同时将isRewardGet设为TRUE
     * 如果isFinished 是False,返回False;
     * @param userId
     * @param achievementId
     * @return
     */
    boolean getAchievementCash(UserId userId, String achievementId);

    /**
     * 更新成就信息
     *如果process>=1 isFinished设为true
     * @param userId
     * @return
     */
    boolean updateAchievementCash(UserId userId,String achievementId,double process);


}
