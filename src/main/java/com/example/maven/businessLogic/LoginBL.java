package com.example.maven.businessLogic;

public class LoginBL {
    public boolean login(String userId, String password){
        System.out.println(userId);
        System.out.println(password);
        if(userId == null) return false;
        //test
        if (userId.equals("2333"))
            return true;
        else
            return false;
    }
}
