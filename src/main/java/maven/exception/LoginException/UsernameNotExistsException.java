package maven.exception.LoginException;

import maven.exception.util.WrongMessage;

public class UsernameNotExistsException extends Exception{
    private WrongMessage wrongMessage;

    public UsernameNotExistsException(){
        wrongMessage = new WrongMessage("UsernameNotExists","Username does not exist!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
