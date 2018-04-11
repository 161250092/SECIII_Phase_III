package com.example.maven.businessLogic.loginBL;

public interface LoginBLService {
    /**
     * 判断是否登陆成功
     * @param userName 用户昵称
     * @param password 用户密码
     * @return 是否登陆成功
     */
    boolean login(String userName, String password);
}
