package maven.exception.LoginException;

import maven.exception.util.WrongMessage;
import maven.model.primitiveType.UserId;

public class WorkerLoginException extends Exception{
    private WrongMessage wrongMessage;
    private String userId;

    public WorkerLoginException(UserId userId){
        wrongMessage = new WrongMessage("WorkerLogin","Worker Login!");
        this.userId = userId.value;
    }

    public String getUserId() {
        return userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
