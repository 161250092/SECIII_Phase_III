package maven.data.RequestorData;

import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;
import maven.model.task.PublishedTaskDetail;
import maven.model.user.Requestor;
import maven.model.task.AcceptedTask;
import maven.model.task.PublishedTask;

import java.util.List;

public interface RequestorDataService {

    /**
     * 上传欲发布的任务信息
     * @param publishedTask 发布的任务信息
     * @return 上传任务信息的状态
     */
    public boolean saveTaskInfo(PublishedTask publishedTask);

    /**
     * 修改任务信息
     * @param publishedTaskDetail
     * @return
     */
    public boolean reviseTaskInfo(PublishedTaskDetail publishedTaskDetail);

    /**
     * 撤销已发布的任务
     * @param taskId 任务Id
     * @return 后端处理任务撤销请求的结果
     */
    public boolean revokeTask(TaskId taskId);

    /**
     * 修改已发布的任务（追加任务的悬赏金额）
     * @param taskId 任务Id
     * @return 后端处理任务修改请求的结果
     */
    public boolean reviseTask(TaskId taskId);

    /**
     * 获取发布者已发布的任务
     * @param userId 发布者Id
     * @return 已发布的任务列表
     */
    List<PublishedTask> getPublishedTaskList(UserId userId);

    /**
     * 获取工人已完成并待审核的任务列表
     * @param taskId 任务Id
     * @return 待审核的任务列表
     */
    public List<AcceptedTask> getSubmittedTaskList(TaskId taskId);

    /**
     * 审批工人的任务
     * @param taskId 任务Id
     * @param userId 工人Id
     * @param acceptedTaskState 任务状态
     * @return 后台处理的结果
     */
    public boolean approvalTask(TaskId taskId, UserId userId, AcceptedTaskState acceptedTaskState);

    /**
     * 获取发布且已完成任务的列表
     * @param userId 发布者Id
     * @return 发布且已完成任务的列表
     */
    public List<PublishedTask> getAssignedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取发布但未完成任务的列表
     * @param userId 发布者Id
     * @return 发布但未完成任务的列表
     */
    public List<PublishedTask> getAssignedButIncompleteTaskList(UserId userId);

    /**
     * 获取具体的发布任务的信息
     * @param userId 发布者Id
     * @param taskId 任务Id
     * @return 任务详情
     */
    public PublishedTask getAssignedTask(UserId userId, TaskId taskId);

    /**
     * 查看已接受某任务的工人任务完成情况
     * @param taskId 任务Id
     * @return 任务列表
     */
    public List<AcceptedTask> getAcceptedTaskList(TaskId taskId);


    /**
     * 获取发布者的个人信息
     * @param userId 发布者Id
     * @return Requestor对象
     */
    Requestor getRequestorInfo(UserId userId);
}
