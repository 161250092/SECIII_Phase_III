package maven.exception.LoginException;

import maven.exception.util.WrongMessage;
import maven.model.primitiveType.UserId;

public class RequestorLoginException extends Exception{
    private WrongMessage wrongMessage;
    private String userId;

    public RequestorLoginException(UserId userId){
        wrongMessage = new WrongMessage("RequestorLogin","Requestor Login!");
        this.userId = userId.value;
    }

    public String getUserId() {
        return userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
