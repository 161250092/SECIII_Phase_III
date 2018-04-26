package com.example.maven.controller;

import com.example.maven.businessLogic.userBL.UserBLService;
import com.example.maven.businessLogic.userBL.UserBLStub;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserBLService {

    private UserBLService userBL;

    public UserController(){
        userBL = new UserBLStub();
    }

    /**
     * 获取用户Id
     * @param userName 用户昵称
     * @return 用户Id
     */
    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    public String getUserId(String userName){
        return userBL.getUserId(userName);
    }

}
