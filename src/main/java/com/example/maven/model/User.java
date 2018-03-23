package com.example.maven.model;

import java.util.ArrayList;

public class User {
    //账户信息
    private String userID;
    private String password;
    private String userName;

    //发布的标注任务 数组
    private ArrayList<ImageSet> assignments;
    //已标注的图片编号 数组
    private ArrayList<String> taggedImages;

    public User(String userID, String password, String userName) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
    }


}