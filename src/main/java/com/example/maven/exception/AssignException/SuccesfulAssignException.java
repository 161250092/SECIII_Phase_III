package com.example.maven.exception.AssignException;

import com.example.maven.exception.WrongMessage;

public class SuccesfulAssignException extends Exception{
    private WrongMessage wrongMessage;

    public SuccesfulAssignException(){
        wrongMessage = new WrongMessage("AssignSuccess", "The task is assigned successfully!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
