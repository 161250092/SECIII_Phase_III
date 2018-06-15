package maven.businessLogic.massTaskBL.massTaskWorkerBL;

import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.WorkerBidVO;

import java.util.List;

public interface MassTaskWorkerBLService {

    /**
     * 工人申请接受大任务
     * @param workerBidVO 工人竞标VO
     * @return 是否申请成功
     */
    Exception bidForMassTask(WorkerBidVO workerBidVO);


    List<PublishedMassTaskVO> getAvailableMassTask();
}
