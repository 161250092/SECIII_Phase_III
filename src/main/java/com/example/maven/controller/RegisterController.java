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
        registerBL = new RegisterBLStub();
    }

    /**
     * 判断是否重名
     * @param userName 用户昵称
     * @return 是否重名
     */
    @RequestMapping(value = "/isUserNameExist", method = RequestMethod.GET)
    public boolean isExist(String userName){
        return registerBL.isExist(userName);
    }

    /**
     * 判断是否注册成功
     * @param userName 用户昵称
     * @param password 用户密码
     * @return 是否注册成功
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public boolean register(String userName, String password){
        return registerBL.register(userName, password);
    }

}
