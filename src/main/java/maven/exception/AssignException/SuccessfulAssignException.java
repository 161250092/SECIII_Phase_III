package maven.exception.AssignException;

import maven.exception.WrongMessage;

public class SuccessfulAssignException extends Exception{
    private WrongMessage wrongMessage;

    public SuccessfulAssignException(){
        wrongMessage = new WrongMessage("AssignSuccess", "The task is assigned successfully!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}