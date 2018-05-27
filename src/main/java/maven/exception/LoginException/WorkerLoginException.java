package maven.exception.LoginException;

import maven.exception.util.WrongMessage;
import maven.model.primitiveType.UserId;

public class WorkerLoginException extends Exception{
    private WrongMessage wrongMessage;
    private UserId userId;

    public WorkerLoginException(UserId userId){
        wrongMessage = new WrongMessage("WorkerLogin","Worker Login!");
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
