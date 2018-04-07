package com.example.maven.controller.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /**
     * 判断是否注册成功
     * @param userName 用户昵称
     * @return 用户Id
     */
    @RequestMapping(value = "/UserController/getUserId", method = RequestMethod.GET)
    public String getUserId(String userName, String password){
        return null;
    }

}
