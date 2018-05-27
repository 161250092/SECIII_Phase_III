package maven.exception.RegisterException;

import maven.exception.util.WrongMessage;

public class RegisterSuccessException extends Exception{
    private WrongMessage wrongMessage;
    private String userId;

    public RegisterSuccessException(String userId) {
        wrongMessage = new WrongMessage("RegisterSuccess","User registered successfully!");
        this.userId = userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

    public String getUserId() {
        return userId;
    }

}
