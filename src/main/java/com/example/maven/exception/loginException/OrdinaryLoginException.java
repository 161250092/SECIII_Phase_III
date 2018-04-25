package com.example.maven.exception.loginException;

import com.example.maven.exception.WrongMessage;

public class OrdinaryLoginException extends Exception{
    private WrongMessage wrongMessage;

    public OrdinaryLoginException(){
        wrongMessage = new WrongMessage("OrdinaryLogin","Ordinary Login!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
