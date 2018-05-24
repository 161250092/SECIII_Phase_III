package businessLogic.loginBL;

import exception.loginException.*;
import model.primitiveType.Password;
import model.primitiveType.Username;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(Username username, Password password) {
        return null;
    }
}
