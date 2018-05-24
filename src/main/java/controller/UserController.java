package controller;

import businessLogic.userBL.UserBLImpl;
import businessLogic.userBL.UserBLService;
import model.primitiveType.Username;
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

}
