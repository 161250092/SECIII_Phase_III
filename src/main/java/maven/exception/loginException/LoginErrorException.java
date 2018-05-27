package maven.exception.loginException;

import maven.exception.WrongMessage;

public class LoginErrorException extends Exception{
    private WrongMessage wrongMessage;

    public LoginErrorException(){
        wrongMessage = new WrongMessage("LoginError","Password does not match to UserName!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}