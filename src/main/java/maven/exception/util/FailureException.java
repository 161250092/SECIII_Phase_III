package maven.exception.util;

public class FailureException {
    private WrongMessage wrongMessage;

    public FailureException(){
        wrongMessage = new WrongMessage("Failure", "Failure!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
