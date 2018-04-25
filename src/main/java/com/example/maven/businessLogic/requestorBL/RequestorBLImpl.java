package com.example.maven.businessLogic.requestorBL;

import com.example.maven.data.TaskData.TaskDataImpl;
import com.example.maven.data.TaskData.TaskDataService;
import com.example.maven.model.po.Task;

import java.util.ArrayList;
import java.util.List;

public class RequestorBLImpl implements RequestorBLService{

    private TaskDataService taskDataService;

    public RequestorBLImpl(){
        taskDataService = new TaskDataImpl();
    }

    @Override
    public void uploadImages() {

    }

    @Override
    public Exception assignTask(String taskJSON) {
        return null;
    }

    @Override
    public List<Task> getAssignedAndAccomplishedTaskList(String userId) {
        List<String> taskIdlist = taskDataService.getAllAccomplishedAssignedTaskID(userId);
        List<Task> taskList = new ArrayList<>();
        Task tempTask = null;
        for(String taskId : taskIdlist){
            tempTask = taskDataService.getTask(taskId);
            //假如无法通过taskId找到对应的Task，则返回null
            if(tempTask == null)
                return null;
            taskList.add(tempTask);
        }

        return taskList;
    }

    @Override
    public List<Task> getAssignedButIncompleteTaskList(String userId) {
        return null;
    }

    @Override
    public int getAssignedTaskNum() {
        return 0;
    }
}
