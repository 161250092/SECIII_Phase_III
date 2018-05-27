package maven.exception.loginException;

import maven.exception.WrongMessage;

public class AdministerLoginException extends Exception{
    private WrongMessage wrongMessage;

    public AdministerLoginException(){
        wrongMessage = new WrongMessage("AdministerLogin","Administer Login!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

}
