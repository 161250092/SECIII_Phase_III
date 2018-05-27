package maven.exception.loginException;

import maven.exception.WrongMessage;

public class IncompleteInformationException extends Exception{
    private WrongMessage wrongMessage;

    public IncompleteInformationException(){
        wrongMessage = new WrongMessage("IncompleteInformation","Information is incomplete!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}