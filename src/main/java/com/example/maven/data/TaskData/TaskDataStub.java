package com.example.maven.data.TaskData;

import com.example.maven.model.po.Task;

import java.util.List;
import java.util.ArrayList;
public class TaskDataStub implements TaskDataService{
    /**
     *获取用户发布的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAssignedTaskID(String userId){
        List<String> TaskID = new ArrayList<String>();
        return TaskID;
    }

    /**
     *获取用户发布的所有完成的任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAssignedTaskID(String userId){
        List<String> TaskID = new ArrayList<String>();
        return TaskID;
    }

    /**
     * 获取用户接受的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAcceptedTaskID(String userId){
        List<String> TaskID = new ArrayList<String>();
        return TaskID;
    }

    /**
     * 获取用户接受的所有完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAcceptedTaskID(String userId){
        List<String> TaskID = new ArrayList<String>();
        return TaskID;
    }

    /**
     * 任务发布时保存任务信息
     */
    public Boolean saveTask(String userId,Task task){
        return true;
    }

    /**
     * 修改任务信息：完成度+1
     */
    public Boolean reviseTask(String taskId){
        return true;
    }

    /**
     * 获取单个任务信息
     */
    public Task getTask(String taskId){
        return null;
    }
}
