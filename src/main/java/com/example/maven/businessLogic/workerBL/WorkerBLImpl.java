package com.example.maven.businessLogic.workerBL;

import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.data.UserData.UserDataImpl;
import com.example.maven.data.UserData.UserDataService;
import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;
import com.example.maven.model.po.User;

import java.util.ArrayList;
import java.util.List;

public class WorkerBLImpl implements WorkerBLService {
    private TaskDataService taskDataService;
    private UserDataService userDataService;

    public WorkerBLImpl(){
        taskDataService = new TaskDataImpl();
        userDataService = new UserDataImpl();
    }

    @Override
    public List<Task> getAcceptedAndAccomplishedTaskList(String userId) {
        return getTaskList(userId, true);
    }

    @Override
    public List<Task> getAcceptedButIncompleteTaskList(String userId) {
        return  getTaskList(userId, false);
    }

    @Override
    public Task getTaskById(String taskId) {
        return taskDataService.getTask(taskId);
    }

    @Override
    public int getUserScore(String userId) {
        User user = userDataService.getUser(userId);
        return user.getScore();
    }

    @Override
    public int getUserRanking(String userId) {
        List<User> userList = userDataService.getAllUser();
//        List<>


        return 0;
    }

    private List<Task> getTaskList(String userId, boolean isAccomplised){
        List<Task> taskList = new ArrayList<>();
        List<String> taskIdList = null;
        Task task = null;

        if(isAccomplised)
            taskIdList = taskDataService.getAllAccomplishedAcceptedTaskID(userId);
        else
            taskIdList = taskDataService.getAllIncompleteAcceptedTaskID(userId);

        if(taskIdList == null || taskIdList.size() == 0)
            return null;
        else{
            for(int i = 0; i < taskIdList.size(); i++){
                task = taskDataService.getTask(taskIdList.get(i));
                //假如无法通过taskId找到对应的Task，则返回null
                if(task == null)
                    return null;
                taskList.add(task);
            }
            return taskList;
        }
    }



}
