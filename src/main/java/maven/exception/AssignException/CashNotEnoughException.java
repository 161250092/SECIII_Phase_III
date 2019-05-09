package maven.exception.AssignException;

import maven.exception.util.WrongMessage;

public class CashNotEnoughException extends Exception{
    private WrongMessage wrongMessage;

    public CashNotEnoughException(){
        wrongMessage = new WrongMessage("CashNotEnough", "User does not have enough Cash!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
