package com.example.maven.model.po;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;

public class User {
    //用户Id
    private String userId;
    //用户密码
    private String password;
    //用户昵称
    private String userName;
    //发布的标注任务的Id 数组
    private ArrayList<String> publishedTask;
    //已标注的任务的Id 数组
    private ArrayList<String> taggedTask;
    //用户积分
    private int score;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPublishedTask(ArrayList<String> publishedTask) {
        this.publishedTask = publishedTask;
    }

    public void setTaggedTask(ArrayList<String> taggedTask) {
        this.taggedTask = taggedTask;
    }

    public void setScore(int score) { this.score = score; }


    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getPublishedTask() {
        return publishedTask;
    }

    public ArrayList<String> getTaggedTask() {
        return taggedTask;
    }

    public int getScore() { return score; }

    public User(String userId, String password, String userName, ArrayList<String> publishedTask, ArrayList<String> taggedTask, int score) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.publishedTask = publishedTask;
        this.taggedTask = taggedTask;
        this.score = score;
    }


}