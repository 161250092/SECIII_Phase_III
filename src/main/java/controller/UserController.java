package controller;

import businessLogic.userBL.UserBLImpl;
import businessLogic.userBL.UserBLService;
import model.primitiveType.*;
import model.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserBLService userBL;

    public UserController(){
        userBL = new UserBLImpl();
    }

    /**
     * 获取用户Id
     * @param userName 用户昵称
     * @return 用户Id
     */
    @RequestMapping(value = "/user/getUserId", method = RequestMethod.GET)
    public String getUserId(String userName){
        return userBL.getUserId(new Username(userName)).value;
    }


    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
    public User getUserInfo(String username){
        return userBL.getUserInfo(new Username(username));
    }

    /**'
     * 修改用户电子邮件
     * @param username
     * @param email
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reviseUserEmail", method = RequestMethod.GET)
    Exception reviseUserEmail(String username, String email){
        return userBL.reviseUserEmail(new Username(username), new Email(email));
    }

    /**'
     * 修改用户电话号码
     * @param username
     * @param phone
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reviseUserPhone", method = RequestMethod.GET)
    Exception reviseUserPhone(String username, String phone){
        return userBL.reviseUserPhone(new Username(username), new Phone(phone));
    }

    /**
     * 从账户内扣除现金
     * @param username
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reduceCash", method = RequestMethod.GET)
    Exception reduceCash(String username, double cash){
        return userBL.reduceCash(new Username(username), new Cash(cash));
    }

    /**
     * 向账户内转入现金
     * @param username
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/increaseCash", method = RequestMethod.GET)
    Exception increaseCash(String username, double cash){
        return userBL.increaseCash(new Username(username), new Cash(cash));
    }

    /**
     * 从账户内扣除声望
     * 并根据声望修改用户等级、权限
     * @param username
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/reducePrestige", method = RequestMethod.GET)
    Exception reducePrestige(String username, double prestige){
        return userBL.reducePrestige(new Username(username), new Prestige(prestige));
    }

    /**
     * 向账户内增长声望
     * 并根据声望修改用户等级、权限
     * @param username
     * @return 修改是否成功
     */
    @RequestMapping(value = "/user/increasePrestige", method = RequestMethod.GET)
    Exception increasePrestige(String username, double prestige){
        return userBL.increasePrestige(new Username(username), new Prestige(prestige));
    }
}
