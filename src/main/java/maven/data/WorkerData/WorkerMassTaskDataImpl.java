package maven.data.WorkerData;

import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.TaskId;

import java.util.List;

public class WorkerMassTaskDataImpl implements WorkerMassTaskDataService {
    @Override
    public boolean saveWorkerBid(WorkerBid workerBid) {
        return false;
    }

    @Override
    public List<WorkerBid> getAllWorkerBid(TaskId taskId) {
        return null;
    }
}
