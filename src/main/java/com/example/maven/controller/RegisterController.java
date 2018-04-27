package com.example.maven.controller;

import com.example.maven.businessLogic.registerBL.RegisterBLImpl;
import com.example.maven.businessLogic.registerBL.RegisterBLService;
import com.example.maven.businessLogic.registerBL.RegisterBLStub;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController implements RegisterBLService {

    private RegisterBLService registerBL;

    public RegisterController(){
        registerBL = new RegisterBLImpl();
    }

    /**
     * 判断是否重名
     * @param username 用户昵称
     * @return 是否重名
     */
    @RequestMapping(value = "/isUsernameExist", method = RequestMethod.GET)
    public boolean isUsernameExist(String username){
        return registerBL.isUsernameExist(username);
    }

    /**
     * 判断是否注册成功
     * @param username 用户昵称
     * @param password 用户密码
     * @return 是否注册成功
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public boolean register(String username, String password){
        return registerBL.register(username, password);
    }

}
