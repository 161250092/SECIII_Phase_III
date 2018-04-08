package com.example.maven.controller;

import com.example.maven.businessLogic.RegisterBL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterBL registerBL;

    public RegisterController(){
        registerBL = new RegisterBL();
    }

    /**
     * 判断是否重名
     * @param userName 用户昵称
     * @return 是否重名
     */
    @RequestMapping(value = "/RegisterController/isExist", method = RequestMethod.GET)
    public boolean isExist(String userName){
        return registerBL.isExist(userName);
    }

    /**
     * 判断是否注册成功
     * @param userName 用户昵称
     * @param password 用户密码
     * @return
     */
    @RequestMapping(value = "/RegisterController/register", method = RequestMethod.GET)
    public boolean register(String userName, String password){
        return registerBL.register(userName, password);
    }

}
