package maven.businessLogic.workerBL;

import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.user.Worker;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.List;

public interface WorkerBLService {
    /**
     * 获取所有接受且已完成的任务
     * @param userId 工人Id
     * @return 所有已完成的任务
     */
    List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取所有接受但未完成的任务
     * @param userId 工人Id
     * @return 所有未完成的任务
     */
    List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(UserId userId);

    /**
     * 获取当前可以接受的任务
     * @return 所有当前可接受的任务
     */
    List<PublishedTaskVO> getAvailableTaskList(UserId userId);

    /**
     * 众包工人接受任务
     * @param userId 工人Id
     * @param taskIdList 任务Id列表
     * @return 是否接受成功
     */
    Exception acceptTask(UserId userId, List<TaskId> taskIdList);

    /**
     * 众包工人放弃已接受的任务
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 是否放弃成功
     */
    Exception abandonTaskByWorker(UserId userId, TaskId taskId);


    /**
     * 根据任务Id 获取工人已接受的任务
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 任务详情
     */
    AcceptedTask getAcceptedTaskById(UserId userId,TaskId taskId);


    /**
     * 获取众包工人的排名
     * @param userId 工人Id
     * @return 工人排名
     */
    int getUserRanking(UserId userId);

    /**
     * 积分兑换
     * @param userId
     * @param cash
     * @return
     */

    boolean exchange(UserId userId,Cash cash);

}
