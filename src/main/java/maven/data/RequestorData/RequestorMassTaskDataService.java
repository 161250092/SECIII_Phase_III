package maven.data.RequestorData;

import maven.model.massTask.MassTaskDetail;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.user.User;

import java.util.List;

public interface RequestorMassTaskDataService {
    /*                  发布者                    */
    /**
     * 保存大任务信息
     * @param massTaskDetail 任务信息
     * @return 是否保存
     */
    boolean saveMassTaskDetail(MassTaskDetail massTaskDetail);

    /**
     * 获得该发布者发布的所有大任务的细节
     * @param requestorId 发布者ID
     * @return 该发布者发布的所有大任务的细节
     */
    List<MassTaskDetail> getAllMassTaskDetailOfThisRequestor(UserId requestorId);

    /**
     * 判断该任务是否为大任务
     * @param taskId 任务ID
     * @return 该任务是否为大任务
     */
    boolean isMassTask(TaskId taskId);

    /**
     * 获得该大任务的细节
     * @param taskId 大任务ID
     * @return 该大任务的细节；若ID不是大任务，则返回null
     */
    MassTaskDetail getMassTaskDetailOfThisTask(TaskId taskId);

    /*                  工人                   */
    /**
    /**
     * 获得所有可接受的大任务的细节
     * @param searchDate 查找时的日期
     * @return 所有可接受的大任务的细节
     */
    List<MassTaskDetail> getAllAvailableMassTaskDetail(long searchDate);

    /*                  算法                   */
    /**
    /**
     * 获得所有过了截止日期但没有被分配的大任务
     * @param allocateTaskDate 分配任务的时间点
     * @return 所有过了截止日期但没有被分配的大任务
     */
    List<MassTaskDetail> getAllExpiredAndNotAllocatedMassTaskDetail(long allocateTaskDate);
}
