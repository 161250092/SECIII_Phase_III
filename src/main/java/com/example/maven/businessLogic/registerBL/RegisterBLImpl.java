package com.example.maven.businessLogic.registerBL;

public class RegisterBLImpl implements RegisterBLService {

    public boolean isExist(String userName){
        System.out.println(userName);
        //test
        if (userName.equals("2333")||userName.equals("wwww"))
            return false;
        else
            return true;
    }

    public boolean register(String userName, String password){
        //System.out.println(userName);
        //System.out.println(password);
        //test
        if (userName.equals("wwww"))
            return false;
        else
            return true;
    }
}
