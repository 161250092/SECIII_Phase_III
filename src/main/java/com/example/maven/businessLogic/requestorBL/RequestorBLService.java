package com.example.maven.businessLogic.requestorBL;

import com.example.maven.model.po.Task;

import java.util.List;

public interface RequestorBLService {
    void uploadImages();
    /**
     * 发布任务
     * @param taskJSON 任务的JSON字符串
     * @return 后端处理发布请求的结果
     */
    Exception assignTask(String taskJSON);

    /**
     * 获取发布且已完成任务的列表
     * @param userId 用户Id
     * @return 发布且已完成任务的列表
     */
    List<Task> getAssignedAndAccomplishedTaskList(String userId);

    /**
     * 获取发布但未完成任务的列表
     * @param userId 用户Id
     * @return 发布但未完成任务的列表
     */
    List<Task> getAssignedButIncompleteTaskList(String userId);

    /**
     * 获取发布任务总数
     * @return 发布任务总数
     */
    int getAssignedTaskNum();
}
