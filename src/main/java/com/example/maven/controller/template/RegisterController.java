package com.example.maven.controller.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    /**
     * 判断是否重名
     * @param userId 用户Id
     * @return 是否重名
     */
    @RequestMapping(value = "/RegisterController/isExist", method = RequestMethod.GET)
    public boolean isExist(String userId){
        return false;
    }

    /**
     * 判断是否注册成功
     * @param userId 用户
     * @return
     */
    @RequestMapping(value = "/RegisterController/register", method = RequestMethod.GET)
    public boolean register(String userId){
        return false;
    }
}
