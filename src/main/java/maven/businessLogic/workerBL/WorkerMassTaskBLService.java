package maven.businessLogic.workerBL;

import maven.model.massTask.WorkerBid;
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

    /**
     * 获得所有可用的大任务
     * @param searchDate 搜索日期
     * @return 所有可用的大任务
     */
    List<PublishedMassTaskVO> getAllAvailableMassTask(long searchDate);

    /**
     * 获得工人自己投标的大任务信息
     * @param workerId 工人ID
     * @return 该工人自己投标的大任务信息
     */
    List<WorkerBid> getWorkerAllBidsInfo(UserId workerId);
}
