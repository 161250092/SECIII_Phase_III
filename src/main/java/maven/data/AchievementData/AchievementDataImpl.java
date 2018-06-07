package maven.data.AchievementData;

import maven.businessLogic.achievementBL.AchievementBLService;
import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;

import java.util.List;

public class AchievementDataImpl implements AchievementDataService {
    @Override
    public List<Achievement> getUserAchievement(UserId userId) {
        return null;
    }

    @Override
    public boolean getAchievementCash(UserId userId, String achievementId) {
        return false;
    }

    @Override
    public boolean updateAchievementCash(UserId userId) {
        return false;
    }
}
