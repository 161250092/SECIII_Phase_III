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
     * @param userName 用户昵称
     * @param password 用户密码
     * @return 是否登陆成功
     */
    @RequestMapping(value = "/LoginController/login", method = RequestMethod.GET)
    public boolean login(String userName, String password){
        return loginBL.login(userName, password);
    }

}
