package com.example.maven.businessLogic.workerBL;

import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;

import java.util.ArrayList;
import java.util.List;

public class WorkerBLImpl implements WorkerBLService {
    private TaskDataService taskDataService;

    public WorkerBLImpl(){
        taskDataService = new TaskDataImpl();
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
        return 0;
    }

    @Override
    public int getUserRanking(String userId) {
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
                taskList.add(task);
            }
            return taskList;
        }
    }



}
