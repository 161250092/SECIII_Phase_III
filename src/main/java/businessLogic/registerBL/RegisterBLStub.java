package businessLogic.registerBL;

import model.primitiveType.Password;
import model.primitiveType.Username;

public class RegisterBLStub implements RegisterBLService{

    @Override
    public boolean isUsernameExist(Username username) {
        return false;
    }

    @Override
    public Exception register(Username username, Password password) {
        return null;
    }
}
