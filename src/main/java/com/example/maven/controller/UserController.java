package com.example.maven.controller;

import com.example.maven.businessLogic.userBL.UserBLService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserBLService {

    /**
     * 获取用户Id
     * @param userName 用户昵称
     * @return 用户Id
     */
    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    public String getUserId(String userName){
        return "123456";
    }

}
