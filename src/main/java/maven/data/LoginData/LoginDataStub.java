package maven.data.LoginData;

import model.primitiveType.Password;
import model.primitiveType.Username;

public class LoginDataStub implements LoginDataService {
    /**
     * 登陆：管理员、用户、工人
     * @param username
     * @param password
     * @return区别登陆类型
     */
    public Exception Login(Username username, Password password){
        return null;
    }
}
