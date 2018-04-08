package com.example.maven.businessLogic;

public class RegisterBL {

    public boolean isExist(String userName){

        //test
        if (userName.equals("2333"))
            return false;
        else
            return true;
    }

    public boolean register(String userName, String password){
        //test
        if (userName.equals("wwww"))
            return false;
        else
            return true;
    }
}
