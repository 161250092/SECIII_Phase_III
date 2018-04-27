package com.example.maven.businessLogic.loginBL;

import com.example.maven.exception.loginException.AdministerLoginException;
import com.example.maven.exception.loginException.IncompleteInformationException;
import com.example.maven.exception.loginException.OrdinaryLoginException;
import com.example.maven.exception.loginException.LoginErrorException;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        if(username == null || password == null)
            return new IncompleteInformationException();
        //test
        if (username.equals("2333"))
            return new OrdinaryLoginException();
        else if (username.equals("admin"))
            return new AdministerLoginException();
        else
            return new LoginErrorException();
    }
}
