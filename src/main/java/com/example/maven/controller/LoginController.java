package com.example.maven.controller;

import com.example.maven.businessLogic.LoginBL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginBL loginBL;

    public LoginController(){
        loginBL = new LoginBL();
    }

    /**
     * 判断是否登陆成功
     * @param userId 用户ID
     * @param password 用户密码
     * @return 是否登陆成功
     */
    @RequestMapping(value = "/LoginController/login", method = RequestMethod.GET)
    public boolean login(String userId, String password){
        return loginBL.login(userId, password);
    }

}
