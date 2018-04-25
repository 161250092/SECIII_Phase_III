package com.example.maven.businessLogic.userBL;

import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.model.po.User;

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
