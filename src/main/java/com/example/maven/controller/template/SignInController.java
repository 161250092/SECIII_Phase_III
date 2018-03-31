package com.example.maven.controller.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    /*
    判断是否重名
    */
    @RequestMapping(value = "/SignInController/isExist", method = RequestMethod.GET)
    public boolean isExist(String userId){
        return false;
    }

    /*
    判断是否注册成功
    */
    @RequestMapping(value = "/SignInController/register", method = RequestMethod.GET)
    public boolean register(String userId){
        return false;
    }

    /*
    判断是否登陆成功
    */
    @RequestMapping(value = "/SignInController/signIn", method = RequestMethod.GET)
    public boolean signIn(String userId, String password){
        return false;
    }


}
