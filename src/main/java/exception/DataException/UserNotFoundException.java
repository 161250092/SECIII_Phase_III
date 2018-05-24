package exception.DataException;

import exception.WrongMessage;
import model.po.User;

public class UserNotFoundException extends Exception{
    private WrongMessage wrongMessage;

    public UserNotFoundException(){
        wrongMessage = new WrongMessage("UserNotFound","This task does not exist!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
