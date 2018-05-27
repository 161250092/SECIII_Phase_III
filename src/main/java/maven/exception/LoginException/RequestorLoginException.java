package maven.exception.LoginException;

import maven.exception.util.WrongMessage;
import maven.model.primitiveType.UserId;

public class RequestorLoginException extends Exception{
    private WrongMessage wrongMessage;
    private UserId userId;

    public RequestorLoginException(UserId userId){
        wrongMessage = new WrongMessage("RequestorLogin","Requestor Login!");
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
