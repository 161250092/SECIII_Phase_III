package com.example.maven.controller.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    /**
     * 判断是否登陆成功
     * @param userId 用户Id
     * @param password 用户密码
     * @return 是否登陆成功
     */
    @RequestMapping(value = "/LoginController/login", method = RequestMethod.GET)
    public boolean login(String userId, String password){
        return false;
    }

    /**
     * 获取用户昵称
     * @param userId 用户Id
     * @return 用户昵称
     */

    @RequestMapping(value = "/LoginController/getUserName", method = RequestMethod.GET)
    public boolean login(String userId){
        return false;
    }

}
