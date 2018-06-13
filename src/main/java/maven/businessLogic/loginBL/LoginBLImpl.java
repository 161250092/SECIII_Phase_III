package maven.businessLogic.loginBL;

import maven.data.AdminData.AdminDataImpl;
import maven.data.AdminData.AdminDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.exception.LoginException.*;
import maven.model.primitiveType.Password;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Username;
import maven.model.user.Admin;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

import java.util.List;

public class LoginBLImpl implements LoginBLService {
    private UserDataService userDataService;
    private AdminDataService adminDataService;

    public LoginBLImpl(){
        userDataService = new UserDataImpl();
        adminDataService = new AdminDataImpl();
    }

    @Override
    public Exception login(Username username, Password password) {
        List<Username> usernameList = userDataService.getAllUsernameList();
        for(Username name : usernameList){
            if(username.value.equals(name.value)){
                UserId userId = userDataService.getUserId(name);
                User user = userDataService.getUserByUserId(userId);
                if(password.value.equals(user.getPassword().value)){
                    if(user instanceof Worker)
                        return new WorkerLoginException(userId);
                    else if(user instanceof Requestor)
                        return new RequestorLoginException(userId);
                }
                else
                    return new LoginErrorException();
            }
        }

        List<Admin> adminList = adminDataService.getAllAdmin();
        for(Admin admin : adminList){
            if(username.value.equals(admin.getUsername().value)){
                if(password.value.equals(admin.getPassword().value))
                    return new AdministerLoginException(admin.getUserId());
                else
                    return new LoginErrorException();
            }
        }
        return new UsernameNotExistsException();
    }
}
