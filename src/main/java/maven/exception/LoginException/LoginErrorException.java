package maven.exception.LoginException;

import maven.exception.util.WrongMessage;

public class LoginErrorException extends Exception{
    private WrongMessage wrongMessage;

    public LoginErrorException(){
        wrongMessage = new WrongMessage("LoginError","Password does not match to UserName!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
