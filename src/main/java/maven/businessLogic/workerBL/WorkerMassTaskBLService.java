package maven.businessLogic.workerBL;

import maven.model.primitiveType.UserId;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.WorkerBidVO;

import java.util.List;

public interface WorkerMassTaskBLService {

    /**
     * 工人申请接受大任务
     * @param workerBidVO 工人的竞标VO
     * @return 是否申请成功
     */
    Exception bidForMassTask(WorkerBidVO workerBidVO);


    List<PublishedMassTaskVO> getAllAvailableMassTask();

    List<WorkerBidVO> getWorkerAllBidsInfo(UserId workerId);
}
