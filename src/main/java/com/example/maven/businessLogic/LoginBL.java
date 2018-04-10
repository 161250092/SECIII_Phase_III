package com.example.maven.businessLogic;

public class LoginBL {
    public boolean login(String userName, String password){
        System.out.println(userName);
        System.out.println(password);
        if(userName == null) return false;
        //test
        if (userName.equals("2333"))
            return true;
        else
            return false;
    }
}
