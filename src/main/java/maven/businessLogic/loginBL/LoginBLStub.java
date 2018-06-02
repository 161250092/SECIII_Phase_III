package maven.businessLogic.loginBL;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.Username;
import maven.model.primitiveType.UserId;

import maven.exception.LoginException.WorkerLoginException;
import maven.exception.LoginException.RequestorLoginException;

public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(Username username, Password password) {
        return new RequestorLoginException(new UserId("161250092"));
    }
}
