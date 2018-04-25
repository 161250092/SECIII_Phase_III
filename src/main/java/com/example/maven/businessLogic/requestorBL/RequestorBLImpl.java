package com.example.maven.businessLogic.requestorBL;

import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.exception.AssignException.SuccesfulAssignException;
import com.example.maven.model.po.Task;
import com.example.maven.model.po.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.security.auth.login.FailedLoginException;
import java.util.ArrayList;
import java.util.List;

public class RequestorBLImpl implements RequestorBLService{

    private UserDataService userDataService;
    private TaskDataService taskDataService;

    public RequestorBLImpl(){
        taskDataService = new TaskDataImpl();
        userDataService = new UserDataImpl();
    }

    /**
     * ------未完成------
     *
     */
    @Override
    public void uploadImages() {

    }

    @Override
    public Exception assignTask(String taskJSON) {
        Gson gson = new GsonBuilder().create();
        Task task = gson.fromJson(taskJSON, Task.class);
        String userId = null;

        String taskId = task.getTaskId();
        String[] temp = taskId.split("_");
        userId = temp[0];

        if(taskDataService.saveTask(userId, task))
            return new SuccesfulAssignException();
        return new FailedLoginException();
    }

    @Override
    public List<Task> getAssignedAndAccomplishedTaskList(String userId) {
        return getTaskList(userId,true);
    }

    @Override
    public List<Task> getAssignedButIncompleteTaskList(String userId) {
        return getTaskList(userId,false);
    }

    @Override
    public int getAssignedTaskNum() {
        int numOfTasks = 0;
        int numOfIncompleteTasks = 0;
        int numOfAccomplishedTasks = 0;

        List<User> userList = userDataService.getAllUser();
        String userId = null;
        for(User user : userList){
            userId = user.getUserId();
            numOfAccomplishedTasks += taskDataService.getAllAccomplishedAssignedTaskID(userId).size();
            numOfIncompleteTasks += taskDataService.getAllIncompleteAssignedTaskID(userId).size();
        }
        numOfTasks = numOfIncompleteTasks + numOfAccomplishedTasks;
        return numOfTasks;
    }

    /**
     * 获取用户已接受的任务列表
     * @param userId 用户Id
     * @param isAccomplised 判断任务是否已完成
     * @return 任务列表
     */
    private List<Task> getTaskList(String userId, boolean isAccomplised){
        List<Task> taskList = new ArrayList<>();
        List<String> taskIdList = null;
        Task task = null;

        if(isAccomplised)
            taskIdList = taskDataService.getAllAccomplishedAssignedTaskID(userId);
        else
            taskIdList = taskDataService.getAllIncompleteAssignedTaskID(userId);

        if(taskIdList == null || taskIdList.size() == 0)
            return null;
        else{
            for(int i = 0; i < taskIdList.size(); i++){
                task = taskDataService.getTask(taskIdList.get(i));
                //假如无法通过taskId找到对应的Task，则返回null
                if(task == null)
                    return null;
                taskList.add(task);
                taskList.add(task);
            }
            return taskList;
        }
    }
}
