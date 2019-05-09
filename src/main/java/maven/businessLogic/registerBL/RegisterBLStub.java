package maven.businessLogic.registerBL;

import maven.exception.RegisterException.RegisterSuccessException;
import maven.exception.util.SuccessException;
import maven.model.primitiveType.Email;
import maven.model.primitiveType.Password;
import maven.model.primitiveType.Phone;
import maven.model.primitiveType.Username;

public class RegisterBLStub implements RegisterBLService{

    @Override
    public boolean isUsernameExist(Username username) {
        return false;
    }

    @Override
    public Exception registerRequesotr(Username username, Password password, Email email, Phone phone) {
        return new RegisterSuccessException("test1111");
    }

    @Override
    public Exception registerWorker(Username username, Password password, Email email, Phone phone) {
        return new RegisterSuccessException("test2222");
    }
}
