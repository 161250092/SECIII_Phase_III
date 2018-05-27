package maven.data.WorkerData;

import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.user.Worker;
import maven.model.task.PublishedTask;

import java.util.List;

public interface WorkDataService {
    /**
     * 获取所有接受且已完成的任务
     * @param userId 工人Id
     * @return 所有已完成的任务
     */
    public List<AcceptedTask> getAcceptedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取所有接受但未完成的任务
     * @param userId 工人Id
     * @return 所有未完成的任务
     */
    public List<AcceptedTask> getAcceptedButIncompleteTaskList(UserId userId);

    /**
     * 获取当前可以接受的任务
     * @return 所有当前可接受的任务
     */
    public List<PublishedTask> getAvailableTaskList();

    /**
     * 众包工人接受任务
     * @param acceptedTask 任务信息
     * @return 是否接受成功
     */
    public boolean acceptTask(AcceptedTask acceptedTask);

    /**
     * 众包工人放弃已接受的任务
     * @param taskId 工人Id
     * @return 是否放弃成功
     */
    public boolean abandonTaskByWorker(UserId userId, TaskId taskId);

    /**
     * 获取已接受任务的信息
     * @param userId
     * @param taskId
     * @return
     */
    public AcceptedTask getAcceptedTaskById(UserId userId,TaskId taskId);

    /**
     * 获取所有工人信息
     * @return
     */
    public List<Worker> getAllWorker();

    /**
     * 获取工人的个人信息
     * @param userId 工人Id
     * @return Worker对象
     */
    public Worker getWorkerInfo(UserId userId);
}
