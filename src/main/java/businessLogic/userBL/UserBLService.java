package businessLogic.userBL;

import model.primitiveType.*;
import model.user.User;

public interface UserBLService {
    /**
     * 根据用户名获取用户Id
     * @param username 用户名
     * @return 用户Id
     */
    UserId getUserId(Username username);

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getUserInfo(Username username);

    /**'
     * 修改用户电子邮件
     * @param username
     * @param email
     * @return 修改是否成功
     */
    Exception reviseUserEmail(Username username, Email email);

    /**'
     * 修改用户电话号码
     * @param username
     * @param phone
     * @return 修改是否成功
     */
    Exception reviseUserPhone(Username username, Phone phone);

    /**
     * 从账户内扣除现金
     * @param username
     * @return 修改是否成功
     */
    Exception reduceCash(Username username, Cash cash);

    /**
     * 向账户内转入现金
     * @param username
     * @return 修改是否成功
     */
    Exception increaseCash(Username username, Cash cash);

    /**
     * 从账户内扣除声望
     * 并根据声望修改用户等级、权限
     * @param username
     * @return 修改是否成功
     */
    Exception reducePrestige(Username username, Prestige prestige);

    /**
     * 向账户内增长声望
     * 并根据声望修改用户等级、权限
     * @param username
     * @return 修改是否成功
     */
    Exception increasePrestige(Username username, Prestige prestige);
}
