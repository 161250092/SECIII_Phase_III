package maven.businessLogic.workerBL;

import maven.businessLogic.algorithm.LinearRegression;
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
import maven.model.massTask.MassTaskPricingMechanism;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.task.PublishedTaskState;
import maven.model.vo.MassTaskDetailVO;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.PublishedTaskVO;
import maven.model.vo.WorkerBidVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerMassTaskBLImpl implements WorkerMassTaskBLService{

    private WorkerMassTaskDataService workerMassTaskData;
    private RequestorMassTaskDataService requestorMassTaskData;
    private RequestorDataService requestorData;

    private LinearRegression linearRegression;

    public WorkerMassTaskBLImpl(){
        workerMassTaskData = new WorkerMassTaskDataImpl();
        requestorMassTaskData = new RequestorMassTaskDataImpl();
        requestorData = new RequestorDataImpl();
        linearRegression = new LinearRegression();
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
    public List<PublishedMassTaskVO> getAllAvailableMassTask(UserId workerId, long searchDate) {
        List<PublishedMassTaskVO> publishedMassTaskVOList = new ArrayList<>();

        Set<String> biddenTaskIdSet = new HashSet<>();
        List<WorkerBid> bidsOfThisWorker = workerMassTaskData.getAllBidOfThisWorker(workerId);
        for (WorkerBid bid: bidsOfThisWorker){
            biddenTaskIdSet.add(bid.getChosenTaskId().value);
        }

        List<MassTaskDetail> massTaskDetails = requestorMassTaskData.getAllAvailableMassTaskDetail(searchDate);
        for (MassTaskDetail massTaskDetail: massTaskDetails){
            if (!biddenTaskIdSet.contains(massTaskDetail.getTaskId().value)){
                PublishedTask tempPublishedTask = requestorData.getPublishedTask(massTaskDetail.getTaskId());

                if(tempPublishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE){
                    List<WorkerBid> tempBidsOfThisTask = workerMassTaskData.getAllWorkerBidOfThisTask(tempPublishedTask.getTaskId());

                    double estimateUnitPrice;
                    if(tempBidsOfThisTask.size() <= 1){
                        if(massTaskDetail.getMassTaskPricingMechanism() == MassTaskPricingMechanism.MAXIMIZE_TASKS){
                            estimateUnitPrice = massTaskDetail.getBudget().value / (double)( 2 * tempPublishedTask.getImageFilenameList().size() );
                        }else {
                            estimateUnitPrice = massTaskDetail.getGivenUnitPrice().value;
                        }
                    }else {
                        estimateUnitPrice = linearRegression.getEstimateUnitPrice(tempBidsOfThisTask, tempPublishedTask.getImageFilenameList().size());
                    }

                    massTaskDetail.setGivenUnitPrice(new Cash(estimateUnitPrice));

                    publishedMassTaskVOList.add(new PublishedMassTaskVO(
                            new PublishedTaskVO(tempPublishedTask), new MassTaskDetailVO(massTaskDetail)
                    ));
                }
            }
        }
        return publishedMassTaskVOList;
    }

    @Override
    public List<WorkerBid> getWorkerAllBidsInfo(UserId workerId) {
        return workerMassTaskData.getAllBidOfThisWorker(workerId);
    }
}
