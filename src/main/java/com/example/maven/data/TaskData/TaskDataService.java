package com.example.maven.data.TaskData;

import com.example.maven.model.po.Task;

import java.util.List;

public interface TaskDataService {
    /**
     *获取用户发布的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAssignedTaskID(String userId);

    /**
     *获取用户发布的所有完成的任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAssignedTaskID(String userId);

    /**
     * 获取用户接受的所有未完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllIncompleteAcceptedTaskID(String userId);

    /**
     * 获取用户接受的所有完成任务的ID
     * @return 所有任务的IDList
     */
    public List<String> getAllAccomplishedAcceptedTaskID(String userId);

    /**
     * 任务发布时保存任务信息
     */
    public Boolean saveTask(String userId,Task task);

    /**
     * 修改任务信息：完成度+1
     */
    public Boolean reviseTask(String taskId);

    /**
     * 获取单个任务信息
     */
    public Task getTask(String taskId);

    /**
     * 转移已经完成标注的任务标注数据
     * @param userId
     * @param taskId
     * @return
     */
    public boolean setAcceptedTaskAccomplished(String userId,String taskId);
}
