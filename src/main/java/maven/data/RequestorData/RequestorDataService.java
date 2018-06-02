package maven.data.RequestorData;

import maven.model.primitiveType.Filename;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.WorkerNum;
import maven.model.task.*;
import maven.model.user.Requestor;

import java.util.List;

public interface RequestorDataService {

    /**
     * 上传欲发布的任务信息
     * 覆盖性保存
     * @param publishedTask 发布的任务信息
     * @return 上传任务信息的状态
     */
     boolean saveTaskInfo(PublishedTask publishedTask);

    /**
     * 上传任务的标注信息
     * @param taskId 任务Id
     * @param ImageNum 样本内图片数量
     * @param imageIndexList 图片下标数组
     * @return
     */
     boolean saveTaskSampleInfo(TaskId taskId, int ImageNum, List<Integer> imageIndexList);

    /**
     * 获取样本信息
     * @param taskId 任务Id
     * @return 样本信息
     */
     Sample getSample(TaskId taskId);

    /**
     * 修改任务信息
     * @param taskId 任务Id
     * @param publishedTaskDetail 任务瞬时状态
     * @return
     */
     boolean saveTaskDetail(TaskId taskId, PublishedTaskDetail publishedTaskDetail);

     /**
      * 修改已接受的工人人数
      * @param acceptedWorkerNum 已接受的工人人数
      * @return
      */
     boolean reviseTaskAcceptedWorkerNum(TaskId taskId, WorkerNum acceptedWorkerNum);

     /**
      * 修改已通过审核的工人人数
      * @param finishedWorkerNum 已通过审核的工人人数
      * @return
      */
     boolean reviseTaskFinishedWorkerNum(TaskId taskId, WorkerNum finishedWorkerNum);

    /**
     * 修改已发布任务的任务状态
     * @param taskId 任务Id
     * @param publishedTaskState 任务状态
     * @return
     */
     boolean reviseTaskState(TaskId taskId, PublishedTaskState publishedTaskState);


    /**
     * 获取发布者已发布的任务Id列表
     * @param userId 发布者Id
     * @return 已发布的任务Id列表
     */
    List<TaskId> getPublishedTaskIdList(UserId userId);

    /**
     * 获取工人已完成并待审核的任务列表
     * @param taskId 任务Id
     * @return 待审核的任务列表
     */
     List<AcceptedTask> getSubmittedTaskList(TaskId taskId);

    /**
     * 审批工人的任务
     * @param taskId 任务Id
     * @param userId 工人Id
     * @param acceptedTaskState 任务状态
     * @return 后台处理的结果
     */
     boolean approvalTask(TaskId taskId, UserId userId, AcceptedTaskState acceptedTaskState);

    /**
     * 获取发布且已完成任务的列表
     * @param userId 发布者Id
     * @return 发布且已完成任务的列表
     */
     List<PublishedTask> getAssignedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取发布但未完成任务的列表
     * @param userId 发布者Id
     * @return 发布但未完成任务的列表
     */
     List<PublishedTask> getAssignedButIncompleteTaskList(UserId userId);

    /**
     * 获取具体的发布任务的信息
     * @param userId 发布者Id
     * @param taskId 任务Id
     * @return 任务详情
     */
     PublishedTask getAssignedTask(UserId userId, TaskId taskId);

    /**
     * 查看已接受某任务的工人任务完成情况
     * @param taskId 任务Id
     * @return 任务列表
     */
     List<AcceptedTask> getAcceptedTaskList(TaskId taskId);
}
