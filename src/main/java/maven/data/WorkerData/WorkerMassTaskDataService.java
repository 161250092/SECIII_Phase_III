package maven.data.WorkerData;

import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;

import java.util.List;

public interface WorkerMassTaskDataService {
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

    /**
     * 获取接受任务的WorkerBid
     * @param taskId 任务ID
     * @return WorkerBid的List
     */
    List<WorkerBid> getAllWorkerBidOfThisTask(TaskId taskId);
}
