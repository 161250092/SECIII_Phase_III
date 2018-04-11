package com.example.maven.data.TaskData;

import com.example.maven.model.po.Task;
import com.example.maven.service.DataService.TaskDataService;

import java.util.List;

public class TaskDataImpl implements TaskDataService{
    /**
     *获取用户发布的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAssignedTaskID(String userId){
        return null;
    }

    /**
     *获取用户发布的所有完成的任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAssignedTaskID(String userId){
        return null;
    }

    /**
     * 获取用户接受的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAcceptedTaskID(String userId){
        return null;
    }

    /**
     * 获取用户接受的所有完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAcceptedTaskID(String userId){
        return null;
    }

    /**
     * 任务发布前获取任务ID
     * @param userId 用户ID
     */
    public String getTaskID(String userId){
        return null;
    }

    /**
     * 任务发布时保存任务信息
     */
    public Boolean saveTask(String userId,Task task){
        return false;
    }

    /**
     * 修改任务信息：完成度+1
     */
    public Boolean reviseTask(String taskId){
        return false;
    }

    /**
     * 获取单个任务信息
     */
    public Task getTask(String taskId){
        return null;
    }
}
