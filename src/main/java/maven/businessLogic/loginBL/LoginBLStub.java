package maven.businessLogic.loginBL;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.Username;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(Username username, Password password) {
        return null;
    }
}
