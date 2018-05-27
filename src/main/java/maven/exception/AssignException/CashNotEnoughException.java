package maven.exception.AssignException;

import maven.exception.util.WrongMessage;

public class CashNotEnoughException extends Exception{
    private WrongMessage wrongMessage;

    public CashNotEnoughException(){
        wrongMessage = new WrongMessage("CashNotEnough", "The requestor does not have enough Cash to assign a Task!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
