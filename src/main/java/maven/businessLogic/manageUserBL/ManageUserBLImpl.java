package maven.businessLogic.manageUserBL;

import maven.data.Map.MapDataImpl;
import maven.data.Map.MapDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.Prestige;
import maven.model.primitiveType.TaskNum;
import maven.model.primitiveType.UserId;
import maven.model.task.TaskType;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

import java.util.List;
import java.util.Map;

public class ManageUserBLImpl implements ManageUserBLService {

    private UserDataService userDataService;
    private MapDataService mapDataService;

    public ManageUserBLImpl(){
        userDataService = new UserDataImpl();
        mapDataService = new MapDataImpl();
    }

    @Override
    public User getUserByUserId(UserId userId) {
        return userDataService.getUserByUserId(userId);
    }

    @Override
    public List<Worker> getAllWorker(){
        return userDataService.getAllWorker();
    }

    @Override
    public List<Requestor> getAllRequestor(){
        return userDataService.getAllRequestor();
    }

    @Override
    public boolean reduceCash(UserId userId, Cash cash) {
        User user = userDataService.getUserByUserId(userId);
        Cash lastCash = user.getCash();
        Cash currentCash = new Cash(lastCash.value - cash.value);
        return userDataService.reviseCash(userId, currentCash);
    }

    @Override
    public boolean increaseCash(UserId userId, Cash cash) {
        User user = userDataService.getUserByUserId(userId);
        Cash lastCash = user.getCash();
        Cash currentCash = new Cash(lastCash.value + cash.value);
        return userDataService.reviseCash(userId, currentCash);
    }

    @Override
    public boolean revisePrestige(UserId userId, Prestige prestige) {
        Map<TaskType,Prestige> taskTypePrestigeMap = mapDataService.getPrestigeTaskType();
        Prestige minPrestige = taskTypePrestigeMap.get(TaskType.ORDINARY_LEVEL_LABEL_REQUIRED);
        Prestige maxPrestige = mapDataService.getMaxPrestige();

        //将要保存到数据库内的声望
        Prestige realPrestige = prestige;
        //如果用户声望将要超过上限，则将用户声望置为最大值
        if(prestige.value > maxPrestige.value)
            realPrestige = maxPrestige;

        //根据声望 修改用户的任务数量
        double delta = prestige.value - minPrestige.value;
        int taskNumber = (int)(delta+1);
        TaskNum taskNum = new TaskNum(taskNumber);

        userDataService.reviseTaskNum(userId, taskNum);

        //修改声望
        return userDataService.revisePrestige(userId, prestige);
    }

    @Override
    public boolean reducePrestige(UserId userId, Prestige prestige) {
        User user = userDataService.getUserByUserId(userId);
        Prestige lastPrestige = user.getPrestige();
        Prestige currentPrestige = new Prestige(lastPrestige.value - prestige.value);
        return revisePrestige(userId, currentPrestige);
    }

    @Override
    public boolean increasePrestige(UserId userId, Prestige prestige) {
        User user = userDataService.getUserByUserId(userId);
        Prestige lastPrestige = user.getPrestige();
        Prestige currentPrestige = new Prestige(lastPrestige.value + prestige.value);
        return revisePrestige(userId, currentPrestige);
    }
}
