package com.example.maven.exception.loginException;

import com.example.maven.exception.WrongMessage;
import com.example.maven.model.po.User;

public class UserException extends Exception{
    private WrongMessage wrongMessage;
    private String userId;

    public UserException(String userId, String type){
        if(type.equals("Ordinary"))
            wrongMessage = new WrongMessage("OrdinaryLogin","Ordinary Login!");
        if(type.equals("Admin"))
            wrongMessage = new WrongMessage("AdministerLogin","Administer Login!");
        this.userId = userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

    public String getUserId(){return userId;}
}
