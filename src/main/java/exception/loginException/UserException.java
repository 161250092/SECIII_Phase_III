package exception.loginException;

import exception.WrongMessage;
import model.po.User;

public class UserException extends Exception{
    private WrongMessage wrongMessage;
    private String userId;

    public UserException(String userId, String type){
        if(type.equals("Ordinary"))
            wrongMessage = new WrongMessage("OrdinaryLogin","Ordinary Login!");
        if(type.equals("Admin"))
            wrongMessage = new WrongMessage("AdministerLogin","Administer Login!");
        // 新增注册时触发的异常
        if(type.equals("SuccessfulRegister"))
            wrongMessage = new WrongMessage("SuccessfulRegister", "Successful Register!");
        if(type.equals("FailedRegister"))
            wrongMessage = new WrongMessage("FailedRegister","Failed Register!");
        this.userId = userId;
    }

    public WrongMessage getWrongMessage() {
        return wrongMessage;
    }

    public String getUserId(){return userId;}
}
