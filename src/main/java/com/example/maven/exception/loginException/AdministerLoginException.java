package com.example.maven.exception.loginException;

import com.example.maven.exception.WrongMessage;

public class AdministerLoginException extends Exception{
    private WrongMessage wrongMessage;

    public AdministerLoginException(){
        wrongMessage = new WrongMessage("AdministerLogin","Administer Login!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

}
