package maven.exception.LoginException;

import maven.exception.util.WrongMessage;
import maven.model.primitiveType.UserId;

public class AdministerLoginException extends Exception{
    private WrongMessage wrongMessage;
    private String userId;

    public AdministerLoginException(UserId userId){
        wrongMessage = new WrongMessage("AdministerLogin","Administer Login!");
        this.userId = userId.value;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

}
