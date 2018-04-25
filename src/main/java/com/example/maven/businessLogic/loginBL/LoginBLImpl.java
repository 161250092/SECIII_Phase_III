package com.example.maven.businessLogic.loginBL;

import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.exception.loginException.AdministerLoginException;
import com.example.maven.exception.loginException.OrdinaryLoginException;
import com.example.maven.exception.loginException.LoginErrorException;
import com.example.maven.model.po.User;

import java.util.List;

public class LoginBLImpl implements LoginBLService {
    private UserDataService userDataService;

    public LoginBLImpl(){
        userDataService = new UserDataImpl();
    }

    public Exception login(String userName, String password){
        List<User> userList = userDataService.getAllUser();
        for(User user : userList){
            if(user.getUserName().equals(userName)){
                 if(user.getPassword().equals(password)) {
                     if(user.isAdmin())
                         return new AdministerLoginException();
                     else
                        return new OrdinaryLoginException();
                 }
                 else
                     return new LoginErrorException();
            }
        }
        return new LoginErrorException();
    }
}
