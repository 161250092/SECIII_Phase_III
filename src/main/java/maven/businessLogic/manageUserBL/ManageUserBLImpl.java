package maven.businessLogic.manageUserBL;

import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.Prestige;
import maven.model.primitiveType.UserId;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

import java.util.List;

public class ManageUserBLImpl implements ManageUserBLService {

    private UserDataService userDataService;

    public ManageUserBLImpl(){
        userDataService = new UserDataImpl();
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
        return userDataService.revisePrestige(userId, prestige);
    }

    @Override
    public boolean reducePrestige(UserId userId, Prestige prestige) {
        User user = userDataService.getUserByUserId(userId);
        Prestige lastPrestige = user.getPrestige();
        Prestige currentPrestige = new Prestige(lastPrestige.value - prestige.value);
        return userDataService.revisePrestige(userId, currentPrestige);
    }

    @Override
    public boolean increasePrestige(UserId userId, Prestige prestige) {
        User user = userDataService.getUserByUserId(userId);
        Prestige lastPrestige = user.getPrestige();
        Prestige currentPrestige = new Prestige(lastPrestige.value + prestige.value);
        return userDataService.revisePrestige(userId, currentPrestige);
    }
}
