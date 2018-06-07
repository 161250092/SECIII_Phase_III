package maven.data.RequestorData;

import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Requestor;

import java.util.List;
import java.util.Map;

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
     Sample getSample(TaskId taskId,UserId userId);

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
     boolean revisePublishedTaskState(TaskId taskId, PublishedTaskState publishedTaskState);


    /**
     * 获取发布者已发布的任务Id列表
     * @param userId 发布者Id
     * @return 已发布的任务Id列表
     */
    List<TaskId> getPublishedTaskIdList(UserId userId);


    /**
     * 获取所有任务的列表
     * @param userId 发布者Id
     * @return 所有任务的列表
     */
     List<PublishedTask> getPublishedTaskList(UserId userId);

    /**
     * 获取具体的发布任务信息
     * @param taskId 任务Id
     * @return 任务详情
     */
     PublishedTask getPublishedTask(TaskId taskId);

    /**
     * 获取任务等级
     * @param taskId 任务ID
     * @return 任务等级
     */
     TaskType getTaskType(TaskId taskId);

    /**
     * 保存任务等级
     * @param taskId 任务ID
     * @param taskType 任务等级
     * @return 是否保存
     */
     boolean saveTaskType(TaskId taskId,TaskType taskType);

    /**
     * 获取金额任务等级映射
     * @return 金额 任务等级映射
     */
     Map getCashTaskType();

    /**
     * 获取任务等级信誉值映射
     * @return 信誉 任务等级映射
     */
     Map getPrestigeTaskType();

}
