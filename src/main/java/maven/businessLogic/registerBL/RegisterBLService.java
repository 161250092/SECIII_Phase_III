package maven.businessLogic.registerBL;

import maven.model.primitiveType.Password;
import maven.model.primitiveType.Username;

public interface RegisterBLService {
    /**
     * 判断是否重名
     * @param username 用户昵称
     * @return 是否重名
     */
    boolean isUsernameExist(Username username);

    /**
     * 判断是否注册成功
     * @param username 用户昵称
     * @param password 用户密码
     * @return
     */
    Exception register(Username username, Password password);
}
