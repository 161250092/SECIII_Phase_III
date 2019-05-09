package maven.exception.DataException;

import maven.exception.util.WrongMessage;

public class UserNotFoundException extends Exception{
    private WrongMessage wrongMessage;

    public UserNotFoundException(){
        wrongMessage = new WrongMessage("UserNotFound","This task does not exist!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
