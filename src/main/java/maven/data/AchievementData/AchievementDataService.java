package maven.data.AchievementData;

import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;

import java.util.ArrayList;
import java.util.List;

public interface AchievementDataService {
    /**
     * 将Achievement的LIST插入数据库
     * @param list 成就信息
     * @return 是否保存
     */


    boolean init_user_achievement(ArrayList<Achievement> list);


    /**
     *  获取用户的成就
     * @param userId 用户ID
     * @return 用户成就
     */
    List<Achievement>   getUserAchievement(UserId userId);

    /**
     * 判断是否可以领取成就奖励
     * @param userId 用户ID
     * @param achievementId 成就ID
     * @return  *if( isFinished == true && isRewardGet == false )  { isRewardGet =true; return true}
     */
    boolean getAchievementCash(UserId userId, String achievementId);

    /**
     * 更新成就信息
     *如果process>=1 isFinished设为true
     * @param userId 用户ID
     * @return 是否更新
     */


    boolean updateAchievementCash(UserId userId,String achievementId,double process);



}
