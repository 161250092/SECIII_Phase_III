package maven.exception.LoginException;

import maven.exception.util.WrongMessage;

public class AdministerLoginException extends Exception{
    private WrongMessage wrongMessage;

    public AdministerLoginException(){
        wrongMessage = new WrongMessage("AdministerLogin","Administer Login!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

}
