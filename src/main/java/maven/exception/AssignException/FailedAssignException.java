package maven.exception.AssignException;

import maven.exception.WrongMessage;

public class FailedAssignException extends Exception{
    private WrongMessage wrongMessage;

    public FailedAssignException(){
        wrongMessage = new WrongMessage("FailedAssign", "The task is assigned unsuccessfully!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
