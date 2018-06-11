package maven.businessLogic.manageUserBL;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.Prestige;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.user.Worker;

import java.util.List;

public interface ManageUserBLService {

    /**
     * 获取用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
    User getUserByUserId(UserId userId);

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
     * 从账户内扣除现金
     * @param userId 用户Id
     * @param cash 现金
     * @return 修改是否成功
     */
    boolean reduceCash(UserId userId, Cash cash);

    /**
     * 向账户内转入现金
     * @param userId 用户Id
     * @param cash 现金
     * @return 修改是否成功
     */
    boolean increaseCash(UserId userId, Cash cash);

    /**
     * 从账户内扣除声望
     * 并根据声望修改用户等级、权限
     * @param userId 用户Id
     * @param prestige 声望
     * @return 修改是否成功
     */
    boolean reducePrestige(UserId userId, Prestige prestige);

    /**
     * 向账户内增长声望
     * 并根据声望修改用户等级、权限
     * @param userId 用户Id
     * @param prestige 声望
     * @return 修改是否成功
     */
    boolean increasePrestige(UserId userId, Prestige prestige);

    /**
     * 更新账户声望
     * @param userId 用户Id
     * @param prestige 声望
     * @return 修改是否成功
     */
    boolean revisePrestige(UserId userId, Prestige prestige);
}
