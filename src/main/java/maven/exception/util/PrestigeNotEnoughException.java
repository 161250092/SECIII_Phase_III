package maven.exception.util;

import maven.exception.util.WrongMessage;

public class PrestigeNotEnoughException {
    private WrongMessage wrongMessage;

    public PrestigeNotEnoughException(){
        wrongMessage = new WrongMessage("PrestigeNotEnough", "User does not have enough Prestige!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
