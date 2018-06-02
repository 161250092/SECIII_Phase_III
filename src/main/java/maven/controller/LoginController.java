package maven.controller;

import maven.businessLogic.loginBL.LoginBLImpl;
import maven.businessLogic.loginBL.LoginBLService;
import maven.businessLogic.loginBL.LoginBLStub;
import maven.model.primitiveType.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginBLService loginBL;

    public LoginController(){
        loginBL = new LoginBLStub();
    }

    /**
     * 判断是否登陆成功
     * @param username 用户昵称
     * @param password 用户密码
     * @return 登录状态
     */
    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public Exception login(String username, String password){
        return loginBL.login(new Username(username), new Password(password));
    }

}