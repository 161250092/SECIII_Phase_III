package businessLogic.userBL;

import data.UserData.UserDataImpl;
import data.UserData.UserDataService;
import model.po.User;

import java.util.List;

public class UserBLImpl implements UserBLService{
    private UserDataService userDataService;

    public UserBLImpl(){
        userDataService = new UserDataImpl();
    }

    @Override
    public String getUserId(String userName) {
        List<User> userList = userDataService.getAllUser();
        for(User user : userList){
            if(user.getUserName().equals(userName))
                return user.getUserId();
        }
        return null;
    }
}
