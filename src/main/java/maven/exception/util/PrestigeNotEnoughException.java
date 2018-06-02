package maven.exception.util;

public class PrestigeNotEnoughException extends Exception{
    private WrongMessage wrongMessage;

    public PrestigeNotEnoughException(){
        wrongMessage = new WrongMessage("PrestigeNotEnough", "User does not have enough Prestige!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
