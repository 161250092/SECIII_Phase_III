package businessLogic.workerBL;

import model.primitiveType.TaskId;
import model.primitiveType.UserId;

import java.util.List;

public interface WorkerBLService {
    /**
     * 获取所有接受且已完成的任务
     * @param userId 用户Id
     * @return 所有已完成的任务
     */
    List<Task> getAcceptedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取所有接受但未完成的任务
     * @param userId 用户Id
     * @return 所有未完成的任务
     */
    List<Task> getAcceptedButIncompleteTaskList(UserId userId);

    /**
     * 获取当前可以接受的任务
     * @return 所有当前可接受的任务
     */
    List<Task> getAvailableTaskList(UserId userId);

    /**
     * 众包工人接受任务
     * @param userId 用户Id
     * @param taskIListJSON 任务Id列表所转化成的JSON字符串
     * @return 是否接受成功
     */
    boolean acceptTask(UserId userId, String taskIListJSON);


    /**
     * 根据任务Id获取任务
     * @param taskId 任务Id
     * @return 任务详情
     */
    Task getTaskById(TaskId taskId);

    /**
     * 获取用户的积分
     * @param userId 用户编号
     * @return 用户积分
     */
    int getUserScore(UserId userId);

    /**
     * 获取用户的排名
     * @param userId 用户Id
     * @return 用户排名
     */
    int getUserRanking(UserId userId);
}
