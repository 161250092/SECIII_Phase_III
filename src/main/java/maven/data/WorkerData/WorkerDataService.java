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
     * 获取所有接受且已完成的任务
     * @param userId 工人Id
     * @return 所有已完成的任务
     */
     List<AcceptedTask> getAcceptedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取某工人所有接受任务完成状况的详情
     * @param userId 工人Id
     * @return 所有的任务
     */
     List<AcceptedTask> getAcceptedTaskListByUserId(UserId userId);

    /**
     * 获取某任务的所有工人完成状况的详情
     * @param taskId 任务Id
     * @return 所有任务
     */
    List<AcceptedTask> getAcceptedTaskListByTaskId(TaskId taskId);

    /**
     * 众包工人接受任务
     * @param acceptedTask 任务信息
     * @return 是否接受成功
     */
     boolean acceptTask(AcceptedTask acceptedTask);

    /**
     * 更改任务信息
     * @param userId
     * @param taskId
     * @param acceptedTaskState
     * @return
     */
    boolean reviseAcceptedTaskState(UserId userId, TaskId taskId, AcceptedTaskState acceptedTaskState);

    /**
     * 获取已接受任务的信息
     * @param userId
     * @param taskId
     * @return
     */
     AcceptedTask getAcceptedTaskById(UserId userId,TaskId taskId);
}
