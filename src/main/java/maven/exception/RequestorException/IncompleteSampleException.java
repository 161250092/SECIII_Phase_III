package maven.exception.RequestorException;

import maven.exception.util.WrongMessage;

public class IncompleteSampleException extends Exception{
    private WrongMessage wrongMessage;

    public IncompleteSampleException(){
        wrongMessage = new WrongMessage("IncompleteSample", "The sample is incomplete!");
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }
}
