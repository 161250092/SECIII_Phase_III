package maven.data.WorkerData;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
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
     * 获取所有接受但未完成的任务
     * @param userId 工人Id
     * @return 所有未完成的任务
     */
     List<AcceptedTask> getAcceptedButIncompleteTaskList(UserId userId);

    /**
     * 获取所有已发布但未完成的任务
     * @return 所有已发布但未完成的任务列表
     */
     List<PublishedTask> getIncompleteTaskList();

    /**
     * 众包工人接受任务
     * @param acceptedTask 任务信息
     * @return 是否接受成功
     */
     boolean acceptTask(AcceptedTask acceptedTask);

    /**
     * 众包工人放弃已接受的任务、删除任务信息
     * @param taskId 工人Id
     * @return 是否放弃成功
     */
     boolean abandonTaskByWorker(UserId userId, TaskId taskId);

    /**
     * 获取已接受任务的信息
     * @param userId
     * @param taskId
     * @return
     */
     AcceptedTask getAcceptedTaskById(UserId userId,TaskId taskId);

    /**
     * 获取所有工人信息
     * @return
     */
     List<Worker> getAllWorker();
}
