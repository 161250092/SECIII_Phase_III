package maven.exception.util;

public class WrongMessage {
    private String type;
    private String message;
    public WrongMessage(String type, String message){
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
