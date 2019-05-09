package maven.businessLogic.registerBL;

import maven.model.primitiveType.Email;
import maven.model.primitiveType.Password;
import maven.model.primitiveType.Phone;
import maven.model.primitiveType.Username;

public interface RegisterBLService {
    /**
     * 判断是否重名
     * @param username 用户昵称
     * @return 是否重名
     */
    boolean isUsernameExist(Username username);

    /**
     * 判断发布者是否注册成功
     * @param username 用户昵称
     * @param password 用户密码
     * @param email 邮箱
     * @param phone 电话
     * @return
     */
    Exception registerRequesotr(Username username, Password password, Email email, Phone phone);

    /**
     * 判断工人是否注册成功
     * @param username 用户昵称
     * @param password 用户密码
     * @param email 邮箱
     * @param phone 电话
     * @return
     */
    Exception registerWorker(Username username, Password password, Email email, Phone phone);
}
