package controller;

import businessLogic.loginBL.LoginBLImpl;
import businessLogic.loginBL.LoginBLService;
import businessLogic.loginBL.LoginBLStub;
import model.primitiveType.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginBLService loginBL;

    public LoginController(){
        loginBL = new LoginBLImpl();
    }

    /**
     * 判断是否登陆成功
     * @param username 用户昵称
     * @param password 用户密码
     * @return 登录状态
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Exception login(String username, String password){
        return loginBL.login(new Username(username), new Password(password));
    }

}