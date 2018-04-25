package com.example.maven.exception.DataException;

import com.example.maven.exception.WrongMessage;

public class TaskNotFoundException extends Exception{
    private WrongMessage wrongMessage;

    public TaskNotFoundException(){
        wrongMessage = new WrongMessage("TaskNotFound","This task does not exist!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

}
