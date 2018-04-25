package com.example.maven.exception.loginException;

import com.example.maven.exception.WrongMessage;

public class IncompleteInformationException extends Exception{
    private WrongMessage wrongMessage;

    public IncompleteInformationException(){
        wrongMessage = new WrongMessage("IncompleteInformation","Information is incomplete!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
