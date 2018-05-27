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
     * @param userId 用户Id
     * @return 用户信息
     */
    User getUserInfo(UserId userId);

    /**'
     * 修改用户电子邮件
     * @param userId
     * @param email
     * @return 修改是否成功
     */
    Exception reviseUserEmail(UserId userId, Email email);

    /**'
     * 修改用户电话号码
     * @param userId
     * @param phone
     * @return 修改是否成功
     */
    Exception reviseUserPhone(UserId userId, Phone phone);

    /**
     * 从账户内扣除现金
     * @param userId
     * @return 修改是否成功
     */
    Exception reduceCash(UserId userId, Cash cash);

    /**
     * 向账户内转入现金
     * @param userId
     * @return 修改是否成功
     */
    Exception increaseCash(UserId userId, Cash cash);

    /**
     * 从账户内扣除声望
     * 并根据声望修改用户等级、权限
     * @param userId
     * @return 修改是否成功
     */
    Exception reducePrestige(UserId userId, Prestige prestige);

    /**
     * 向账户内增长声望
     * 并根据声望修改用户等级、权限
     * @param userId
     * @return 修改是否成功
     */
    Exception increasePrestige(UserId userId, Prestige prestige);
}
