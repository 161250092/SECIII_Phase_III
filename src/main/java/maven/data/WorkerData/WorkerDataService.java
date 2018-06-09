package maven.data.WorkerData;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.user.Worker;
import maven.model.task.PublishedTask;

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
     * @param userId 工人ID
     * @param taskId 任务ID
     * @param acceptedTaskState 任务状态
     * @return 是否修改
     */
    boolean reviseAcceptedTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState);

    /**
     * 获取已接受任务的信息
     * @param userId 工人ID
     * @param taskId 任务ID
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
