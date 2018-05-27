package maven.exception.util;

public class SuccessException extends  Exception{
    private WrongMessage wrongMessage;

    public SuccessException(){
        wrongMessage = new WrongMessage("Success", "Success!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
