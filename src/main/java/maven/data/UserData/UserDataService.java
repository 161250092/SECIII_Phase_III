package maven.data.UserData;

import maven.model.primitiveType.*;
import maven.model.user.User;
import maven.model.user.UserLevel;

public interface UserDataService {
    /**
     * 根据用户名获取用户Id
     * @param username 用户名
     * @return 用户Id
     */
    public UserId getUserId(Username username);

    /**
     * 获取用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
    public User getUserInfo(UserId userId);

    /**'
     * 修改用户电子邮件
     * @param userId
     * @param email
     * @return 修改是否成功
     */
    public boolean reviseUserEmail(UserId userId, Email email);

    /**'
     * 修改用户电话号码
     * @param userId
     * @param phone
     * @return 修改是否成功
     */
    public boolean reviseUserPhone(UserId userId, Phone phone);

    /**
     * 修改用户现金
     * @param userId
     * @return 修改是否成功
     */
    public boolean reviseCash(UserId userId, Cash cash);

    /**
     * 修改用户声望
     * 并根据声望修改用户等级、权限
     * @param userId
     * @return 修改是否成功
     */
    public boolean revisePrestige(UserId userId, UserLevel userLevel,Prestige prestige);
}
