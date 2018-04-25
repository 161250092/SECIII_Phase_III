package com.example.maven.exception.DataException;

import com.example.maven.exception.WrongMessage;
import com.example.maven.model.po.User;

public class UserNotFoundException extends Exception{
    private WrongMessage wrongMessage;

    public UserNotFoundException(){
        wrongMessage = new WrongMessage("UserNotFound","This task does not exist!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
