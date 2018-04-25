package com.example.maven.businessLogic.loginBL;

public interface LoginBLService {
    /**
     * 判断是否登陆成功及登陆的用户
     * @param userName 用户昵称
     * @param password 用户密码
     * @return 登陆后的状况
     */
    Exception login(String userName, String password);
}
