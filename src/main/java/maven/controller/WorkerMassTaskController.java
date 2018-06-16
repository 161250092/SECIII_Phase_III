package maven.controller;

import maven.businessLogic.workerBL.WorkerMassTaskBLImpl;
import maven.businessLogic.workerBL.WorkerMassTaskBLService;
import maven.model.JsonConverter;
import maven.model.massTask.WorkerBid;
import maven.model.primitiveType.UserId;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.WorkerBidVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkerMassTaskController {
    private WorkerMassTaskBLService workerMassTaskBL;

    public WorkerMassTaskController(){
        workerMassTaskBL = new WorkerMassTaskBLImpl();
    }

    @RequestMapping(value = "/workerMassTask/bidForMassTask", method = RequestMethod.GET)
    public Exception bidForMassTask(String workerBidVOJson){
        WorkerBidVO workerBidVO = (WorkerBidVO) JsonConverter.jsonToObject(workerBidVOJson, WorkerBidVO.class);
        return workerMassTaskBL.bidForMassTask(workerBidVO);
    }

    @RequestMapping(value = "/workerMassTask/getAllAvailableMassTask", method = RequestMethod.GET)
    public List<PublishedMassTaskVO> getAllAvailableMassTask(String workerId, long searchDate){
        return workerMassTaskBL.getAllAvailableMassTask(new UserId(workerId), searchDate);
    }

    @RequestMapping(value = "/workerMassTask/getWorkerAllBidsInfo", method = RequestMethod.GET)
    public List<WorkerBidVO> getWorkerAllBidsInfo(String workerId){
        List<WorkerBidVO> workerBidVOList = new ArrayList<>();
        List<WorkerBid> workerBids = workerMassTaskBL.getWorkerAllBidsInfo(new UserId(workerId));
        for (WorkerBid workerBid: workerBids){
            workerBidVOList.add(new WorkerBidVO(workerBid));
        }
        return workerBidVOList;
    }
}
