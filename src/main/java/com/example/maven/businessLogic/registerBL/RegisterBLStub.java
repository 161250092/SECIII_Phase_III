package com.example.maven.businessLogic.registerBL;

public class RegisterBLStub implements RegisterBLService{
    @Override
    public boolean isUsernameExist(String userName) {
        System.out.println(userName);
        //test
        if (userName.equals("2333")||userName.equals("wwww"))
            return false;
        else
            return true;
    }

    @Override
    public boolean register(String userName, String password) {
        System.out.println(userName);
        System.out.println(password);
        //test
        if (userName.equals("wwww"))
            return false;
        else
            return true;
    }
}
