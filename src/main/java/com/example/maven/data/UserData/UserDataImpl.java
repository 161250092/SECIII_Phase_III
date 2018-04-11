package com.example.maven.data.UserData;

import com.example.maven.model.User;
import com.example.maven.service.DataService.UserDataService;

import java.util.List;

public class UserDataImpl implements UserDataService{
    /**
     *获取用户信息
     */
    public List<User> getAllUser(){
        return null;
    }

    /**
     *创建单个用户
     *创建文件夹
     * @return 用户ID
     */
    public String newUser(String userName,String password){
        return null;
    }

    /**
     *获取单个用户信息
     */
    public User getUser(String userId){
        return null;
    }

    /**
     *修改用户积分
     * @param score 积分更改数值
     */
    public boolean reviseScore(String userId,int score){
        return false;
    }
}
