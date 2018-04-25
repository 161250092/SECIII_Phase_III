package com.example.maven.businessLogic.adminBL;

import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.model.po.User;
import com.example.maven.model.po.WebsiteStatistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class AdminBLImpl implements AdminBLService {

    private TaskDataService taskDataService;
    private UserDataService userDataService;

    public AdminBLImpl(){
        taskDataService = new TaskDataImpl();
        userDataService = new UserDataImpl();
    }


    public String getWebsiteStatistics(){
        int numOfUsers = 0;
        int numOfTasks = 0;
        int numOfIncompleteTasks = 0;
        int numOfAccomplishedTasks = 0;

        List<User> userList = userDataService.getAllUser();
        String userId = null;
        numOfUsers = userList.size();
        for(User user : userList){
            userId = user.getUserId();
            numOfAccomplishedTasks += taskDataService.getAllAccomplishedAssignedTaskID(userId).size();
            numOfIncompleteTasks += taskDataService.getAllIncompleteAssignedTaskID(userId).size();
        }
        numOfTasks = numOfIncompleteTasks + numOfAccomplishedTasks;


        Gson gson = new GsonBuilder().create();
        WebsiteStatistics statistics = new WebsiteStatistics(numOfUsers, numOfTasks, numOfIncompleteTasks, numOfAccomplishedTasks);
        String objectToJson = gson.toJson(statistics);
        return objectToJson;
    }
}
