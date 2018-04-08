package com.example.maven.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * 获取用户Id
     * @param userName 用户昵称
     * @return 用户Id
     */
    @RequestMapping(value = "/UserController/getUserId", method = RequestMethod.GET)
    public String getUserId(String userName){
        return "123456";
    }

}
