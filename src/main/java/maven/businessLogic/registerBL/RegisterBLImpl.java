package maven.businessLogic.registerBL;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.Username;


public class RegisterBLImpl implements RegisterBLService {

    @Override
    public boolean isUsernameExist(Username username) {
        return false;
    }

    @Override
    public Exception register(Username username, Password password) {
        return null;
    }
}
