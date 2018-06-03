package maven.businessLogic.registerBL;

import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.model.primitiveType.Email;
import maven.model.primitiveType.Password;
import maven.model.primitiveType.Phone;
import maven.model.primitiveType.Username;

import java.util.List;


public class RegisterBLImpl implements RegisterBLService {

    private UserDataService userDataService;

    public RegisterBLImpl(){
        userDataService = new UserDataImpl();
    }

    @Override
    public boolean isUsernameExist(Username username) {
        List<Username> usernameList = userDataService.getAllUsernameList();
        for(Username name : usernameList){
            if(username.value.equals(name.value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Exception registerRequesotr(Username username, Password password, Email email, Phone phone) {
        return null;
    }

    @Override
    public Exception registerWorker(Username username, Password password, Email email, Phone phone) {
        return null;
    }


}
