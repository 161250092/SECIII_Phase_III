package maven.exception.RegisterException;

import maven.exception.util.WrongMessage;

public class UsernameExistsException extends Exception{
    private WrongMessage wrongMessage;

    public UsernameExistsException(){
        wrongMessage = new WrongMessage("UsernameExists","Username exists!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
