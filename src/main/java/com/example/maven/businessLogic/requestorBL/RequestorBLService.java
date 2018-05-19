package com.example.maven.businessLogic.requestorBL;

import com.example.maven.model.primitiveType.TaskId;
import com.example.maven.model.primitiveType.UserId;
import com.example.maven.model.task.PublishedTask;
import com.example.maven.model.vo.PublishedTaskVO;

import java.util.List;

public interface RequestorBLService {

    /**
     * 上传欲发布的任务信息
     * @param taskJSON 任务的JSON字符串
     * @return 上传任务信息的状态
     */
    Exception uploadTaskInfo(String taskJSON);

    /**
     * 发布任务
     * @param taskId 任务Id
     * @return 后端处理任务发布请求的结果
     */
    Exception assignTask(TaskId taskId);


    /**
     * 获取工人已完成并待审核的任务列表
     * @param userId 用户Id
     * @return 待审核的任务列表
     */
    List<PublishedTaskVO> getSubmittedTasList(UserId userId);

    /**
     * 审批工人的任务时 选择通过
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */

    boolean passTask(TaskId taskId, UserId userId);

    /**
     * 审批工人的任务时 选择驳回
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */

    boolean rejectTask(TaskId taskId, UserId userId);
    /**
     * 审批工人的任务时 选择废弃
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */

    boolean abandonTask(TaskId taskId, UserId userId);

    /**
     * 获取发布且已完成任务的列表
     * @param userId 用户Id
     * @return 发布且已完成任务的列表
     */
    List<PublishedTask> getAssignedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取发布但未完成任务的列表
     * @param userId 用户Id
     * @return 发布但未完成任务的列表
     */
    List<PublishedTask> getAssignedButIncompleteTaskList(UserId userId);

    /**
     * 获取发布任务总数
     * @return 发布任务总数
     */
    int getAssignedTaskNum();
}
