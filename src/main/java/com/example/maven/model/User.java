package com.example.maven.model;

import java.util.ArrayList;

public class User {
    //用户Id
    private String userId;
    //用户密码
    private String password;
    //用户昵称
    private String userName;
    //发布的标注任务 数组
    private ArrayList<Task> publishedTask;
    //已标注的图片编号 数组
    private ArrayList<Task> taggedTask;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPublishedTask(ArrayList<Task> publishedTask) {
        this.publishedTask = publishedTask;
    }

    public void setTaggedTask(ArrayList<Task> taggedTask) {
        this.taggedTask = taggedTask;
    }


    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Task> getPublishedTask() {
        return publishedTask;
    }

    public ArrayList<Task> getTaggedTask() {
        return taggedTask;
    }

    public User(String userId, String password, String userName, ArrayList<Task> publishedTask, ArrayList<Task> taggedTask) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.publishedTask = publishedTask;
        this.taggedTask = taggedTask;
    }


}