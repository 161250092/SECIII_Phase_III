package maven.businessLogic.allocateMassTaskBL;

import maven.businessLogic.algorithm.PricingAlgorithm;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.RequestorData.RequestorMassTaskDataImpl;
import maven.data.RequestorData.RequestorMassTaskDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.data.WorkerData.WorkerMassTaskDataImpl;
import maven.data.WorkerData.WorkerMassTaskDataService;
import maven.exception.util.FailureException;
import maven.exception.util.SuccessException;
import maven.model.massTask.*;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.LabelScore;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.task.PublishedTask;
import maven.model.task.WorkerDiscount;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllocateMassTaskBLImpl implements AllocateMassTaskBLService{

    private RequestorMassTaskDataService requestorMassTaskData;
    private RequestorDataService requestorData;
    private WorkerMassTaskDataService workerMassTaskData;
    private WorkerDataService workerData;

    private PricingAlgorithm pricingAlgorithm;

    public AllocateMassTaskBLImpl() {
        requestorMassTaskData = new RequestorMassTaskDataImpl();
        requestorData = new RequestorDataImpl();
        workerMassTaskData = new WorkerMassTaskDataImpl();
        workerData = new WorkerDataImpl();
        pricingAlgorithm = new PricingAlgorithm();
    }

    @Override
    public Exception allocateMassTask(long allocateTaskDate) {
        boolean result = false;

        List<MassTaskDetail> massTaskDetails = requestorMassTaskData.getAllExpiredAndNotAllocatedMassTaskDetail(allocateTaskDate);
        for (MassTaskDetail massTaskDetail: massTaskDetails){
            List<WorkerBid> workerBidsOfThisMassTask = workerMassTaskData.getAllWorkerBidOfThisTask(massTaskDetail.getTaskId());

            List<AllocatedTask> allocatedTasks;
            if (massTaskDetail.getMassTaskPricingMechanism() == MassTaskPricingMechanism.MAXIMIZE_TASKS){
                //最大化地分配任务
                allocatedTasks = pricingAlgorithm.maximizeTasks(workerBidsOfThisMassTask, massTaskDetail.getBudget());
            }else {
                //最小化支出
                PublishedTask tempPublishedTask = requestorData.getPublishedTask(massTaskDetail.getTaskId());
                ImageNum totalImageNumOfThisTask = new ImageNum(tempPublishedTask.getImageFilenameList().size());
                allocatedTasks = pricingAlgorithm.minimizePayments(workerBidsOfThisMassTask, totalImageNumOfThisTask, massTaskDetail.getBudget(), massTaskDetail.getGivenUnitPrice());
            }

            //竞标成功的工人ID集合
            Set<String> allocatedWorkerIdSet = new HashSet<>();
            //给工人分配任务，并把他们的竞标状态改为成功
            int fileListStartIndex = 0;
            for (AllocatedTask allocatedTask: allocatedTasks){
                AcceptedTask tempAcceptedTask = new AcceptedTask(
                        allocatedTask.getWorkerId(),massTaskDetail.getTaskId(),new Date(),
                        new Cash(allocatedTask.getImageNum().value*allocatedTask.getImageUnitPrice().value),
                        new WorkerDiscount(),AcceptedTaskState.ACCEPTED,new LabelScore(0)
                );

                result = workerData.acceptTask(tempAcceptedTask);

                result = workerMassTaskData.updateWorkerBidState(allocatedTask.getWorkerId(), massTaskDetail.getTaskId(), WorkerBidState.SUCCESSFUL);
                result = workerMassTaskData.updateWorkerBidImageAllocation(allocatedTask.getWorkerId(), massTaskDetail.getTaskId(),
                        fileListStartIndex, allocatedTask.getImageNum().value);
                allocatedWorkerIdSet.add(allocatedTask.getWorkerId().value);

                fileListStartIndex += allocatedTask.getImageNum().value;
            }

            //把失败竞标的状态改为失败
            for (WorkerBid workerBid: workerBidsOfThisMassTask){
                if (!allocatedWorkerIdSet.contains(workerBid.getWorkerId().value)){
                    result = workerMassTaskData.updateWorkerBidState(workerBid.getWorkerId(), massTaskDetail.getTaskId(), WorkerBidState.FAILED);
                }
            }
        }

        if (result) return new SuccessException();
        else return new FailureException();
    }
}
