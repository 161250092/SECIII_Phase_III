package maven.businessLogic.workerBL;

import maven.businessLogic.algorithm.PricingAlgorithm;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.RequestorData.RequestorMassTaskDataImpl;
import maven.data.RequestorData.RequestorMassTaskDataService;
import maven.data.WorkerData.WorkerMassTaskDataImpl;
import maven.data.WorkerData.WorkerMassTaskDataService;
import maven.exception.util.FailureException;
import maven.exception.util.SuccessException;
import maven.model.massTask.MassTaskDetail;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.vo.MassTaskDetailVO;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.PublishedTaskVO;
import maven.model.vo.WorkerBidVO;

import java.util.ArrayList;
import java.util.List;

public class WorkerMassTaskBLImpl implements WorkerMassTaskBLService{

    private WorkerMassTaskDataService workerMassTaskData;
    private RequestorMassTaskDataService requestorMassTaskData;
    private RequestorDataService requestorData;

    private PricingAlgorithm pricingAlgorithm;

    public WorkerMassTaskBLImpl(){
        workerMassTaskData = new WorkerMassTaskDataImpl();
        requestorMassTaskData = new RequestorMassTaskDataImpl();
        requestorData = new RequestorDataImpl();
        pricingAlgorithm = new PricingAlgorithm();
    }

    @Override
    public Exception bidForMassTask(WorkerBidVO workerBidVO) {
        if(workerMassTaskData.saveWorkerBid(new WorkerBid(workerBidVO))){
            return new SuccessException();
        }else {
            return new FailureException();
        }
    }

    @Override
    public List<PublishedMassTaskVO> getAllAvailableMassTask(long searchDate) {
        List<PublishedMassTaskVO> publishedMassTaskVOList = new ArrayList<>();

        List<MassTaskDetail> massTaskDetails = requestorMassTaskData.getAllAvailableMassTaskDetail(searchDate);
        for (MassTaskDetail massTaskDetail: massTaskDetails){
            PublishedTask tempPublishedTask = requestorData.getPublishedTask(massTaskDetail.getTaskId());

            List<WorkerBid> tempWorkerBids = workerMassTaskData.getAllWorkerBidOfThisTask(massTaskDetail.getTaskId());
            massTaskDetail.setGivenUnitPrice(pricingAlgorithm.getThresholdPrice(tempWorkerBids, massTaskDetail.getBudget()));

            publishedMassTaskVOList.add(new PublishedMassTaskVO(
                    new PublishedTaskVO(tempPublishedTask), new MassTaskDetailVO(massTaskDetail)
            ));
        }
        return publishedMassTaskVOList;
    }

    @Override
    public List<WorkerBid> getWorkerAllBidsInfo(UserId workerId) {
        return workerMassTaskData.getAllBidOfThisWorker(workerId);
    }
}
