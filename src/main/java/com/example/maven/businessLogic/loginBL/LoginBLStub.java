package com.example.maven.businessLogic.loginBL;

import com.example.maven.exception.loginException.AdministerLoginException;
import com.example.maven.exception.loginException.IncompleteInformationException;
import com.example.maven.exception.loginException.OrdinaryLoginException;
import com.example.maven.exception.loginException.LoginErrorException;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(String userName, String password) {
        System.out.println(userName);
        System.out.println(password);
        if(userName == null || password == null)
            return new IncompleteInformationException();
        //test
        if (userName.equals("2333"))
            return new OrdinaryLoginException();
        else if (userName.equals("admin"))
            return new AdministerLoginException();
        else
            return new LoginErrorException();
    }
}
