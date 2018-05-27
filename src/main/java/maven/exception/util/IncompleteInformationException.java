package maven.exception.util;

import maven.exception.util.WrongMessage;

public class IncompleteInformationException extends Exception{
    private WrongMessage wrongMessage;

    public IncompleteInformationException(){
        wrongMessage = new WrongMessage("IncompleteInformation","Information is incomplete!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
