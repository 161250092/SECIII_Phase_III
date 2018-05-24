package businessLogic.loginBL;

import data.UserData.UserDataImpl;
import data.UserData.UserDataService;
import exception.loginException.AdministerLoginException;
import exception.loginException.OrdinaryLoginException;
import exception.loginException.LoginErrorException;
import exception.loginException.UserException;
import model.po.User;
import model.primitiveType.Password;
import model.primitiveType.Username;

import java.util.List;

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
