package maven.data.LoginData;

import maven.model.primitiveType.*;

public interface LoginDataService {
    /**
     * 登陆：管理员、用户、工人
     * @param username
     * @param password
     * @return区别登陆类型
     */
    public Exception Login(Username username, Password password);
}
