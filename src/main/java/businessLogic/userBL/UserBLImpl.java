package businessLogic.userBL;

import data.UserData.UserDataImpl;
import data.UserData.UserDataService;
import model.primitiveType.UserId;
import model.primitiveType.Username;

import java.util.List;

public class UserBLImpl implements UserBLService{
    private UserDataService userDataService;

    public UserBLImpl(){
        userDataService = new UserDataImpl();
    }

    @Override
    public UserId getUserId(Username userName) {
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
