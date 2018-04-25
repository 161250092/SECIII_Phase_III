package com.example.maven.data.UserData;

import com.example.maven.model.po.User;

import java.util.List;
import java.util.ArrayList;

public class UserDataStub implements UserDataService{
    /**
     *获取用户信息
     */
    public List<User> getAllUser(){
        List<User> user = new ArrayList<User>();
        return user;
    }

    /**
     *创建单个用户
     *创建存储数据需要的文件夹
     * @return 用户ID
     */
    public String newUser(String userName,String password){
        return "99999999";
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
        return true;
    }
}
