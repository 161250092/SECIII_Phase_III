package com.example.maven.service.DataService;

import com.example.maven.model.User;
import java.util.List;

public interface UserDataService {

    /**
    *获取用户信息
     */
    public List<User> getAllUser();

    /**
    *创建单个用户
    *创建文件夹
    * @return 用户ID
     */
    public String newUser(String userName,String password);

    /**
    *获取单个用户信息
     */
    public User getUser(String userId);

    /**
    *修改用户积分
    * @param score 积分更改数值
     */
    public Boolean reviseScore(String userId,int score);
}
