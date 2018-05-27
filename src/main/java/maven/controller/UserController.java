package maven.controller;

import maven.businessLogic.userBL.UserBLImpl;
import maven.businessLogic.userBL.UserBLService;
import maven.businessLogic.userBL.UserBLStub;
import maven.model.primitiveType.*;
import maven.model.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserBLService userBL;

    public UserController(){
        userBL = new UserBLStub();
    }

    /**
     * 获取用户Id
     * @param username 用户昵称
     * @return 用户Id
     */
    @RequestMapping(value = "/user/getUserId", method = RequestMethod.GET)
    public UserId getUserId(String username){
        return userBL.getUserId(new Username(username));
    }


    /**
     * 获取用户信息
     * @param userId 用户名
     * @return 用户信息
     */
    @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
    public User getUserInfo(String userId){
        return userBL.getUserInfo(new UserId(userId));
    }

    /**'
     * 修改用户电子邮件
     * @param userId
     * @param email
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reviseUserEmail", method = RequestMethod.GET)
    Exception reviseUserEmail(String userId, String email){
        return userBL.reviseUserEmail(new UserId(userId), new Email(email));
    }

    /**'
     * 修改用户电话号码
     * @param userId
     * @param phone
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reviseUserPhone", method = RequestMethod.GET)
    Exception reviseUserPhone(String userId, String phone){
        return userBL.reviseUserPhone(new UserId(userId), new Phone(phone));
    }

    /**
     * 从账户内扣除现金
     * @param userId
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reduceCash", method = RequestMethod.GET)
    Exception reduceCash(String userId, double cash){
        return userBL.reduceCash(new UserId(userId), new Cash(cash));
    }

    /**
     * 向账户内转入现金
     * @param userId
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/increaseCash", method = RequestMethod.GET)
    Exception increaseCash(String userId, double cash){
        return userBL.increaseCash(new UserId(userId), new Cash(cash));
    }

    /**
     * 从账户内扣除声望
     * 并根据声望修改用户等级、权限
     * @param userId
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reducePrestige", method = RequestMethod.GET)
    Exception reducePrestige(String userId, double prestige){
        return userBL.reducePrestige(new UserId(userId), new Prestige(prestige));
    }

    /**
     * 向账户内增长声望
     * 并根据声望修改用户等级、权限
     * @param userId
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/increasePrestige", method = RequestMethod.GET)
    Exception increasePrestige(String userId, double prestige){
        return userBL.increasePrestige(new UserId(userId), new Prestige(prestige));
    }
}
