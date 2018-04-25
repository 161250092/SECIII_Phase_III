package com.example.maven.data.UserData;

import com.example.maven.model.po.User;

import java.util.List;

public interface UserDataService {

    /**
    *获取用户信息
     */
    public List<User> getAllUser();

    /**
    *创建单个用户
    *创建存储数据需要的文件夹
    * @return 用户ID
     */
    public boolean newUser(String userName,String password);

    /**
    *获取单个用户信息
     */
    public User getUser(String userId);

    /**
    *修改用户积分
    * @param score 积分更改数值
     */
    public boolean reviseScore(String userId,int score);
}
