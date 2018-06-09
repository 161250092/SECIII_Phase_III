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
     * 对于 achievementId=1  设process＝用户的完成任务数/1 ,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=2  设process＝用户的完成任务数/10 ,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=3  设process＝用户ImageLabel完成数/1,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=4  设process＝用户ImageLabel完成树/10,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=5  设process＝用户FrameLabel完成数/1,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=6  设process＝用户FrameLabel完成数/10,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=7  设process＝用户AreaLabel完成数/1,如果 process>=1, isFinished设为TRUE
     * 对于 achievementId=8  设process＝用户AreaLabel完成数/10,如果 process>=1, isFinished设为TRUE
     * @param userId
     * @return
     */
    boolean updateAchievementCash(UserId userId);




}
