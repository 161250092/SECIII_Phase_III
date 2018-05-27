package maven.exception.loginException;

import maven.exception.WrongMessage;

public class OrdinaryLoginException extends Exception{
    private WrongMessage wrongMessage;

    public OrdinaryLoginException(){
        wrongMessage = new WrongMessage("OrdinaryLogin","Ordinary Login!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
