package com.example.maven.controller;

import com.example.maven.businessLogic.loginBL.LoginBLImpl;
import com.example.maven.businessLogic.loginBL.LoginBLService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginBLService {

    private LoginBLService loginBL;

    public LoginController(){
        loginBL = new LoginBLImpl();
    }

    /**
     * 判断是否登陆成功
     * @param userName 用户昵称
     * @param password 用户密码
     * @return 是否登陆成功
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Exception login(String userName, String password){
        return loginBL.login(userName, password);
    }

}