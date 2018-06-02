package maven.exception.RequestorException;

import maven.exception.util.WrongMessage;

public class AssignSuccessException extends Exception{
    private WrongMessage wrongMessage;

    public AssignSuccessException(){
        wrongMessage = new WrongMessage("AssignSuccess", "The task is assigned successfully!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
