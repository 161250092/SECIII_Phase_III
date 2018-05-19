package com.example.maven.businessLogic.loginBL;

import com.example.maven.model.primitiveType.Password;
import com.example.maven.model.primitiveType.Username;

public interface LoginBLService {
    /**
     * 判断是否登陆成功及登陆的用户
     * @param username 用户昵称
     * @param password 用户密码
     * @return 登陆后的状况
     */
    Exception login(Username username, Password password);
}
