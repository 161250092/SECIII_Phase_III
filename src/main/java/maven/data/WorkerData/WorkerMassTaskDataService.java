package maven.data.WorkerData;

import maven.model.massTask.WorkerBid;
import maven.model.massTask.WorkerBidState;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

public interface WorkerMassTaskDataService {
    /*            工人            */
    /**
     * 保存接受大任务的WorkerBid
     * @param workerBid 信息
     * @return 是否保存
     */
    boolean saveWorkerBid(WorkerBid workerBid);

    /**
     * 获取这个工人的所有竞标
     * @param workerId 工人ID
     * @return 这个工人的所有竞标
     */
    List<WorkerBid> getAllBidOfThisWorker(UserId workerId);

    /*           算法， 发布者            */
    /**
     * 获取这个任务中该工人的竞标
     * @param taskId 任务ID
     * @return 这个任务的所有工人竞标
     */
    WorkerBid getWorkerBidOfThisTask(TaskId taskId, UserId workerId);
    /**
     * 获取这个任务的所有工人竞标
     * @param taskId 任务ID
     * @return 这个任务的所有工人竞标
     */
    List<WorkerBid> getAllWorkerBidOfThisTask(TaskId taskId);

    /**
     * 更新工人竞标的状态
     * @param workerId 工人ID
     * @param workerBidState 竞标的新状态
     * @return 是否更新成功
     */
    boolean updateWorkerBidState(UserId workerId, TaskId taskId, WorkerBidState workerBidState);

    /**
     * 更新工人竞标的状态
     * @param workerId 工人ID
     * @param fileListStartIndex 该工人所分到的第一张图片在任务图片列表的下标
     * @param fileListLength 该工人所分到的图片数
     * @return 是否更新成功
     */
    boolean updateWorkerBidImageAllocation(UserId workerId, TaskId taskId, int fileListStartIndex, int fileListLength);
}
