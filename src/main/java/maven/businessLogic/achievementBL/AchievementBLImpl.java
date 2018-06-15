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
        //Achievement  task1 = new Achievement(userId,"1","1_finished","finish 1 task",0,false,false,new Cash(5));
        //Achievement  task2 = new Achievement(userId,"2","10_finished", "finish 10 tasks",0,false,false,new Cash(20));
        //Achievement  task3 = new Achievement(userId,"3",  "1_ImageLabelFinished", "finish 1 ImageLabel",0,false,false,new Cash(5));
        //Achievement  task4 = new Achievement(userId,"4", "10_ImageLabelFinished" ,"finish 10 ImageLabel",0,false,false,new Cash(20));
        //Achievement  task5 = new Achievement(userId,"5",   "1_FrameLabelFinished", "finish 1 FrameLabel",0,false,false,new Cash(5));
        //Achievement  task6 = new Achievement(userId,"6", "10_FrameLabelFinished", "finish 10 FrameLabel",0,false,false,new Cash(20));
        //Achievement  task7 = new Achievement(userId,"7",  "1_AreaLabelFinished","finish 1 AreaLabel",0,false,false,new Cash(5));
        //Achievement  task8 = new Achievement(userId,"8",   "10_AreaLabelFinished", "finish 10 AreaLabel",0,false,false,new Cash(20));
        //
        //ArrayList<Achievement> achievementsList = new ArrayList<Achievement>();
        //
        //achievementsList.add(task1);
        //achievementsList.add(task2);
        //achievementsList.add(task3);
        //achievementsList.add(task4);
        //achievementsList.add(task5);
        //achievementsList.add(task6);
        //achievementsList.add(task7);
        //achievementsList.add(task8);
        //
        //

        return dataService.init_user_achievement(userId);

    }

    @Override
    public List<Achievement> getUserAchievement(UserId userId) {

        return dataService.getUserAchievement(userId);
    }

    @Override
    public boolean getAchievementCash(UserId userId, int achievementId) {
        Cash  reward = AchievementCash(userId,achievementId);
        if(dataService.getAchievementCash(userId,achievementId))
        return mangeuserbl.increaseCash(userId,reward);
        else
            return false;
    }

    @Override
    public boolean updateAchievementCash(UserId userId) {
        List<AcceptedTaskVO> user_taskInfo  = getTasks(userId);
        int total_taskNum  = user_taskInfo.size();
        int ImageLabelNum = getLabelNum(user_taskInfo,"ImageLabel");
        int FrameLabelNum = getLabelNum(user_taskInfo,"FrameLabel");
        int AreaLabelNum = getLabelNum(user_taskInfo,"AreaLabel");

        //dataService.updateAchievementCash(userId,"1",total_taskNum/1.0);
        //dataService.updateAchievementCash(userId,"2",total_taskNum/10);
        //
        //
        //dataService.updateAchievementCash(userId,"3",ImageLabelNum/1.0);
        //dataService.updateAchievementCash(userId,"4",ImageLabelNum/10);
        //
        //
        //dataService.updateAchievementCash(userId,"5",FrameLabelNum/1.0);
        //dataService.updateAchievementCash(userId,"6",FrameLabelNum/10);
        //
        //
        //dataService.updateAchievementCash(userId,"7",AreaLabelNum/1.0);
        //dataService.updateAchievementCash(userId,"8",AreaLabelNum/10);

        return true;
    }


    public List<AcceptedTaskVO> getTasks(UserId userId){
        return workerbl.getAcceptedAndAccomplishedTaskList(userId);
    }

    public int getLabelNum(List<AcceptedTaskVO> list,String labeltype ){
        int result = 0;
        for(int i=0;i<list.size();i++)
            if(list.get(i).getLabelType().equals(labeltype))
                result++;
        return result;
    }

    public Cash AchievementCash(UserId userId, int achievementId){
        List<Achievement> list = getUserAchievement(userId);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getAchievementId()==achievementId)
                return list.get(i).getCash();
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
