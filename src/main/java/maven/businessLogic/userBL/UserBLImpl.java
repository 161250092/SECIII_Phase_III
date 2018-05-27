package maven.businessLogic.userBL;

//import data.UserData.UserDataImpl;
//import data.UserData.UserDataService;
import maven.model.primitiveType.*;
import maven.model.user.User;

import java.util.List;

public class UserBLImpl implements UserBLService{
    //private UserDataService userDataService;

    public UserBLImpl(){
        //userDataService = new UserDataImpl();
    }

    @Override
    public UserId getUserId(Username userName) {
        return null;
    }

    @Override
    public User getUserInfo(UserId userId) {
        return null;
    }

    @Override
    public Exception reviseUserEmail(UserId userId, Email email) {
        return null;
    }

    @Override
    public Exception reviseUserPhone(UserId userId, Phone phone) {
        return null;
    }

    @Override
    public Exception reduceCash(UserId userId, Cash cash) {
        return null;
    }

    @Override
    public Exception increaseCash(UserId userId, Cash cash) {
        return null;
    }

    @Override
    public Exception reducePrestige(UserId userId, Prestige prestige) {
        return null;
    }

    @Override
    public Exception increasePrestige(UserId userId, Prestige prestige) {
        return null;
    }

    //@Override
    //public String getUserId(Username userName) {
    //    List<User> userList = userDataService.getAllUser();
    //    for(User user : userList){
    //        if(user.getUserName().equals(userName))
    //            return user.getUserId();
    //    }
    //    return null;
    //}
}
