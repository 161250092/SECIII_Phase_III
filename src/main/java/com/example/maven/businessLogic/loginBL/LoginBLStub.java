package com.example.maven.businessLogic.loginBL;

import com.example.maven.exception.loginException.*;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        if(username == null || password == null)
            return new IncompleteInformationException();
        //test
        if (username.equals("2333"))
            return new UserException("2333","Ordinary");
        else if (username.equals("admin"))
            return new UserException("admin","Admin");
        else
            return new LoginErrorException();
    }
}
