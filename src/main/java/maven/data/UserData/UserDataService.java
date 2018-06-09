package maven.data.UserData;

import maven.model.primitiveType.*;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

import java.util.List;

public interface UserDataService {


    /**
     * 获取所有用户昵称的信息
     * @return 发布者、工人Name
     */
    List<Username> getAllUsernameList();

    /**
     * 获取所有工人信息
     * @return 工人列表
     */
    List<Worker> getAllWorker();

    /**
     * 获取所有发布者信息
     * @return 发布者列表
     */
    List<Requestor> getAllRequestor();

    /**
     * 注册：保存用户信息
     * @param requestor：用户信息
     * @return 是否保存
     */
    boolean saveRequestorInfo(Requestor requestor);

    /**
     *注册：保存工人信息
     * @param worker 工人信息
     * @return 是否保存
     */
    boolean saveWorkerInfo(Worker worker);

    /**
     * 获取所有信息
     * @return 管理员、用户、工人IDList
     */
     List<UserId> getAllUserIdList();

    /**
     * 根据用户名获取用户Id，用户、工人
     * @param username 用户名
     * @return 用户Id
     */
     UserId getUserId(Username username);

    /**
     * 获取用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
     User getUserByUserId(UserId userId);

    /**'
     * 修改用户电子邮件
     * @param userId 用户ID
     * @param email 电子邮件
     * @return 修改是否成功
     */
     boolean reviseUserEmail(UserId userId, Email email);

    /**'
     * 修改用户电话号码
     * @param userId 用户ID
     * @param phone 电话号码
     * @return 修改是否成功
     */
     boolean reviseUserPhone(UserId userId, Phone phone);

    /**
     * 修改用户现金
     * @param userId 用户ID
     * @param cash 修改后的值
     * @return 修改是否成功
     */
     boolean reviseCash(UserId userId, Cash cash);

    /**
     * 修改用户声望
     * 并根据声望修改用户等级、权限
     * @param userId 用户ID
     * @return 修改是否成功
     */
     boolean revisePrestige(UserId userId,Prestige prestige);
}
