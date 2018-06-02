package maven.businessLogic.loginBL;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.Username;
import maven.model.primitiveType.UserId;
import maven.exception.LoginException.WorkerLoginException;
public class LoginBLStub implements LoginBLService{
    @Override
    public Exception login(Username username, Password password) {
        return new WorkerLoginException(new UserId("161250092"));
    }
}
