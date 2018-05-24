package businessLogic.workerBL;

import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.task.AcceptedTask;
import model.vo.AcceptedTaskVO;
import model.vo.PublishedTaskVO;

import java.util.List;

public interface WorkerBLService {
    /**
     * 获取所有接受且已完成的任务
     * @param userId 用户Id
     * @return 所有已完成的任务
     */
    List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId);

    /**
     * 获取所有接受但未完成的任务
     * @param userId 用户Id
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
     * @param userId 用户Id
     * @param taskIdList 任务Id列表
     * @return 是否接受成功
     */
    boolean acceptTask(UserId userId, TaskId[] taskIdList);


    /**
     * 根据任务Id 获取工人已接受的任务
     * @param taskId 任务Id
     * @return 任务详情
     */
    AcceptedTask getAcceptedTaskById(TaskId taskId);


    /**
     * 获取用户的排名
     * @param userId 用户Id
     * @return 用户排名
     */
    int getUserRanking(UserId userId);
}
