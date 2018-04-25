package com.example.maven.businessLogic.registerBL;

import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.model.po.User;

import java.util.List;

public class RegisterBLImpl implements RegisterBLService {

    private UserDataService userDataService;

    public RegisterBLImpl(){
        userDataService = new UserDataImpl();
    }

    public boolean isExist(String userName){

        List<User> userList = userDataService.getAllUser();
        for(User user : userList){
            if(user.getUserName().equals(userName))
                return true;
        }

        return false;
    }

    public boolean register(String userName, String password){

//        return userDataService.newUser(userName,password);
        return true;
    }
}
