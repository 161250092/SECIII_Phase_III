package maven.businessLogic.workerBL;

import maven.data.WorkerData.WorkerMassTaskDataService;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.UserId;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.WorkerBidVO;

import java.util.List;

public class WorkerMassTaskBLImpl implements WorkerMassTaskBLService{

    private WorkerMassTaskDataService workerMassTaskData;

    @Override
    public Exception bidForMassTask(WorkerBidVO workerBidVO) {
        workerMassTaskData.saveWorkerBid(new WorkerBid(workerBidVO));
        return null;
    }

    @Override
    public List<PublishedMassTaskVO> getAllAvailableMassTask() {
        return null;
    }

    @Override
    public List<WorkerBidVO> getWorkerAllBidsInfo(UserId workerId) {
        return null;
    }
}
