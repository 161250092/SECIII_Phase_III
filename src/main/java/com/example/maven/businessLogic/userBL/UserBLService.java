package com.example.maven.businessLogic.userBL;

public interface UserBLService {
    /**
     * 根据用户名获取用户Id
     * @param userName
     * @return 用户Id
     */
    String getUserId(String userName);
}
