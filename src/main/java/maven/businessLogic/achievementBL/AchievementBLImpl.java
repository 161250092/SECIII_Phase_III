package maven.businessLogic.achievementBL;

import maven.businessLogic.manageUserBL.ManageUserBLImpl;
import maven.businessLogic.manageUserBL.ManageUserBLService;
import maven.businessLogic.userBL.UserBLImpl;
import maven.businessLogic.userBL.UserBLService;
import maven.businessLogic.workerBL.WorkerBLImpl;
import maven.businessLogic.workerBL.WorkerBLService;
import maven.data.AchievementData.AchievementDataImpl;
import maven.data.AchievementData.AchievementDataService;
import maven.data.WorkerData.WorkerDataService;
import maven.model.message.Achievement;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Cash;
import maven.model.task.AcceptedTask;
import maven.model.user.User;
import maven.model.vo.AcceptedTaskVO;

import java.util.ArrayList;
import java.util.List;

public class AchievementBLImpl implements AchievementBLService {

    private WorkerBLService workerbl;
    private AchievementDataService  dataService;
    private ManageUserBLService mangeuserbl;


    public AchievementBLImpl(){
        dataService = new AchievementDataImpl();
        workerbl = new WorkerBLImpl();
        mangeuserbl = new ManageUserBLImpl();
    }

    @Override
    public boolean init_user_achievement(UserId userId) {
        return dataService.init_user_achievement(userId);
    }

    @Override
    public List<Achievement> getUserAchievement(UserId userId) {
        return dataService.getUserAchievement(userId);
    }

    @Override
    public boolean getAchievementCash(UserId userId, int achievementId) {
        Cash  reward = AchievementCash(userId,achievementId);
        if(dataService.testAndGetAchievementReward(userId,achievementId))
            return mangeuserbl.increaseCash(userId,reward);
        else
            return false;
    }

    @Override
    public boolean updateAchievementCash(UserId userId) {
        List<AcceptedTaskVO> user_taskInfo  = workerbl.getAcceptedAndAccomplishedTaskList(userId);;
        int total_taskNum  = user_taskInfo.size();
        int ImageLabelNum = getLabelNum(user_taskInfo,"ImageLabel");
        int FrameLabelNum = getLabelNum(user_taskInfo,"FrameLabel");
        int AreaLabelNum = getLabelNum(user_taskInfo,"AreaLabel");

        dataService.updateAchievementCash(userId,1,total_taskNum/1.0);
        dataService.updateAchievementCash(userId,2,total_taskNum/10.0);


        dataService.updateAchievementCash(userId,3,ImageLabelNum/1.0);
        dataService.updateAchievementCash(userId,4,ImageLabelNum/10.0);


        dataService.updateAchievementCash(userId,5,FrameLabelNum/1.0);
        dataService.updateAchievementCash(userId,6,FrameLabelNum/10.0);


        dataService.updateAchievementCash(userId,7,AreaLabelNum/1.0);
        dataService.updateAchievementCash(userId,8,AreaLabelNum/10.0);

        return true;
    }

    private int getLabelNum(List<AcceptedTaskVO> list,String labeltype ){
        int result = 0;
        for (AcceptedTaskVO aList : list) {
            if (aList.getLabelType().equals(labeltype)) {
                result++;
            }
        }
        return result;
    }

    private Cash AchievementCash(UserId userId, int achievementId){
        List<Achievement> list = getUserAchievement(userId);
        for (Achievement aList : list) {
            if (aList.getAchievementId() == achievementId){
                return aList.getCash();
            }
        }
        return new Cash(0);
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
