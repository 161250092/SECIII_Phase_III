package maven.businessLogic.achievementBL;

import maven.data.AchievementData.AchievementDataImpl;
import maven.data.AchievementData.AchievementDataService;
import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;

import java.util.List;

public class AchievementBLImpl implements AchievementBLService {

    AchievementDataService  dataService;

    public AchievementBLImpl(){
        dataService = new AchievementDataImpl();
    }

    @Override
    public List<Achievement> getUserAchievement(UserId userId) {
        return dataService.getUserAchievement(userId);
    }

    @Override
    public boolean getAchievementCash(UserId userId, String achievementId) {
        return dataService.getAchievementCash(userId,achievementId);
    }

    @Override
    public boolean updateAchievementCash(UserId userId) {
        return dataService.updateAchievementCash(userId);
    }


/**

            ("1","1_finished","finish 1 task",0,false,false,5)
            ("2","10_finished", "finish 10 tasks"，0，false,false,20)

            ("3",  "1_ImageLabelFinished", "finish 1 ImageLabel",0,false,false,5)
            ("4", "10_ImageLabelFinished" ,"finish 10 ImageLabel",0,false,false,20)

           ("5",   "1_FrameLabelFinished", "finish 1 FrameLabel",0,false,false,5)
            ("6", "10.FrameLabelFinished", "finish 10 FrameLabel",0,false,false,20)

            ("7",  "1_AreaLabelFinished","finish 1 AreaLabel",0,false,false,5)
           ("8",   "10_AreaLabelFinished", "finish 10 AreaLabel",0,false,false,20)


**/
}
