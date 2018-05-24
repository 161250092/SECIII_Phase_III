package businessLogic.loginBL;

import data.UserData.UserDataImpl;
import data.UserData.UserDataService;
import model.primitiveType.Password;
import model.primitiveType.Username;

public class LoginBLImpl implements LoginBLService {
    private UserDataService userDataService;

    public LoginBLImpl(){
        userDataService = new UserDataImpl();
    }

    @Override
    public Exception login(Username username, Password password) {
        return null;
    }
}
