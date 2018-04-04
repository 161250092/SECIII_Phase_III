package com.example.maven.controller.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    /*
    判断是否登陆成功
    */
    @RequestMapping(value = "/LoginController/login", method = RequestMethod.GET)
    public boolean login(String userId, String password){
        return false;
    }

}
