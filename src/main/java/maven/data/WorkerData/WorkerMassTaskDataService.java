package maven.data.WorkerData;

import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.TaskId;

import java.util.List;

public interface WorkerMassTaskDataService {

    /**
     * 保存接受大任务的WorkerBid
     * @param workerBid 信息
     * @return 是否保存
     */
    boolean saveWorkerBid(WorkerBid workerBid);


    /**
     * 获取接受任务的WorkerBid
     * @param taskId 任务ID
     * @return WorkerBid的List
     */
    List<WorkerBid> getAllWorkerBid(TaskId taskId);
}
