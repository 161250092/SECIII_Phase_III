package maven.businessLogic.userBL;

import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.exception.util.FailureException;
import maven.exception.util.SuccessException;
import maven.model.primitiveType.*;
import maven.model.user.User;

import java.util.List;

public class UserBLImpl implements UserBLService{
    private UserDataService userDataService;

    public UserBLImpl(){
        userDataService = new UserDataImpl();
    }

    @Override
    public UserId getUserId(Username userName) {
        return userDataService.getUserId(userName);
    }

    @Override
    public User getUserInfo(UserId userId) {
        return userDataService.getUserByUserId(userId);
    }

    @Override
    public Exception reviseUserEmail(UserId userId, Email email) {
        if(userDataService.reviseUserEmail(userId, email))
            return new SuccessException();
        return new FailureException();
    }

    @Override
    public Exception reviseUserPhone(UserId userId, Phone phone) {
        if(userDataService.reviseUserPhone(userId, phone))
            return new SuccessException();
        return new FailureException();
    }

    @Override
    public Exception reduceCash(UserId userId, Cash cash) {
        User user = userDataService.getUserByUserId(userId);
        Cash last_cash = user.getCash();
        if(userDataService.reviseCash(userId ,new Cash(last_cash.value - cash.value)))
            return new SuccessException();
        return new FailureException();
    }

    @Override
    public Exception increaseCash(UserId userId, Cash cash) {
        User user = userDataService.getUserByUserId(userId);
        Cash last_cash = user.getCash();
        if(userDataService.reviseCash(userId ,new Cash(last_cash.value + cash.value)))
            return new SuccessException();
        return new FailureException();
    }

    @Override
    public Exception reducePrestige(UserId userId, Prestige prestige) {
        User user = userDataService.getUserByUserId(userId);
        Prestige last_prestige = user.getPrestige();
        /**
         * 修改用户等级
         * 未完成
         */
        if(userDataService.revisePrestige(userId,  new Prestige(last_prestige.value - prestige.value)))
            return new SuccessException();
        return new FailureException();
    }

    @Override
    public Exception increasePrestige(UserId userId, Prestige prestige) {
        User user = userDataService.getUserByUserId(userId);
        Prestige last_prestige = user.getPrestige();
        /**
         * 修改用户等级
         * 未完成
         */
        if(userDataService.revisePrestige(userId,  new Prestige(last_prestige.value + prestige.value)))
            return new SuccessException();
        return new FailureException();
    }
}
