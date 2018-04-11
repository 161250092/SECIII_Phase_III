package com.example.maven.businessLogic.registerBL;

public interface RegisterBLService {
    /**
     * 判断是否重名
     * @param userName 用户昵称
     * @return 是否重名
     */
    boolean isExist(String userName);

    /**
     * 判断是否注册成功
     * @param userName 用户昵称
     * @param password 用户密码
     * @return
     */
    boolean register(String userName, String password);
}
