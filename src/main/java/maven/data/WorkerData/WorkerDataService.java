package maven.data.WorkerData;

import maven.model.primitiveType.LabelScore;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.user.Worker;
import maven.model.task.PublishedTask;

import java.util.Date;
import java.util.List;

public interface WorkerDataService {

    /**
     * 获取某工人所有接受任务完成状况的详情
     * @param userId 工人Id
     * @return 所有的任务
     */
     List<AcceptedTask> getAcceptedTaskListByUserId(UserId userId);

    /**
     * 众包工人接受任务
     * @param acceptedTask 任务信息
     * @return 是否接受成功
     */
     boolean acceptTask(AcceptedTask acceptedTask);

    /**
     * 更改任务信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @param acceptedTaskState 任务状态
     * @return 是否修改成功
     */
    boolean reviseAcceptedTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState);

    /**
     * 更改任务时间
     * @param userId 工人Id
     * @param taskId 任务Id
     * @param time 时间
     * @return 是否修改成功
     */
    boolean reviseAcceptedTaskTime(UserId userId, TaskId taskId, Date time);

    /**
     * 保存标注评分
     * @param userId 工人Id
     * @param taskId 任务Id
     * @param labelScore 系统评分
     * @return 是否保存成功
     */
    boolean saveLabelScore(UserId userId, TaskId taskId, LabelScore labelScore);

    /**
     * 获取已接受任务的信息
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 任务信息
     */
     AcceptedTask getAcceptedTaskById(UserId userId,TaskId taskId);

    /**
     * 查看已接受某任务的工人任务完成情况
     * @param taskId 任务Id
     * @return 任务列表
     */
    List<AcceptedTask> getAcceptedTaskList(TaskId taskId);
}
