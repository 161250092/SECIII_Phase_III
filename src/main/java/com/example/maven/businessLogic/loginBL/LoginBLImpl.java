package com.example.maven.businessLogic.loginBL;

import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.exception.loginException.AdministerLoginException;
import com.example.maven.exception.loginException.OrdinaryLoginException;
import com.example.maven.exception.loginException.LoginErrorException;
import com.example.maven.exception.loginException.UserException;
import com.example.maven.model.po.User;

import java.util.List;

public class LoginBLImpl implements LoginBLService {
    private UserDataService userDataService;

    public LoginBLImpl(){
        userDataService = new UserDataImpl();
    }

    public Exception login(String username, String password){
        List<User> userList = userDataService.getAllUser();
        for(User user : userList){
            if(user.getUserName().equals(username)){
                 if(user.getPassword().equals(password)) {
                     //获取Id
                     String userId = user.getUserId();
                     //识别身份
                     if(user.isAdmin())
                         return new UserException(userId, "Admin");
                     else
                        return new UserException(userId, "Ordinary");
                 }
                 else
                     return new LoginErrorException();
            }
        }
        return new LoginErrorException();
    }
}
