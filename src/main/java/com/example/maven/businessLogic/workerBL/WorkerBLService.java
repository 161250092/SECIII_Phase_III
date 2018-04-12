package com.example.maven.businessLogic.workerBL;

import com.example.maven.model.po.Label;
import com.example.maven.model.po.Task;

import java.util.List;

public interface WorkerBLService {
    /**
     * 获取所有接受且已完成的任务
     * @param userId 用户Id
     * @return 所有已完成的任务
     */
    List<Task> getAcceptedAndAccomplishedTaskList(String userId);

    /**
     * 获取所有接受但未完成的任务
     * @param userId 用户Id
     * @return 所有未完成的任务
     */
    List<Task> getAcceptedButIncompleteTaskList(String userId);

    /**
     * 根据任务Id获取任务
     * @param taskId 任务Id
     * @return 任务详情
     */
    Task getTaskById(String taskId);

    /**
     * 获取用户的积分
     * @param userId 用户编号
     * @return 用户积分
     */
    int getUserScore(String userId);

    /**
     * 获取用户的排名
     * @param userId 用户Id
     * @return 用户排名
     */
    int getUserRanking(String userId);
}
