package maven.businessLogic.requestorBL;

import maven.businessLogic.manageUserBL.ManageUserBLImpl;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.RequestorData.RequestorMassTaskDataImpl;
import maven.data.RequestorData.RequestorMassTaskDataService;
import maven.exception.AssignException.CashNotEnoughException;
import maven.exception.util.FailureException;
import maven.exception.util.SuccessException;
import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.user.Requestor;
import maven.model.vo.MassTaskDetailVO;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.ArrayList;
import java.util.List;

public class RequestorMassTaskBLImpl implements RequestorMassTaskBLService{

    private RequestorMassTaskDataService requestorMassTaskData;
    private RequestorDataService requestorData;
    private ManageUserBLImpl manageUserBL;

    public RequestorMassTaskBLImpl() {
        requestorMassTaskData = new RequestorMassTaskDataImpl();
        requestorData = new RequestorDataImpl();
        manageUserBL = new ManageUserBLImpl();
    }

    @Override
    public Exception uploadMassTaskDetail(UserId requestorId, MassTaskDetail massTaskDetail) {
        Requestor requestor = (Requestor)manageUserBL.getUserByUserId(requestorId);

        boolean r1, r2;
        if(requestor.getCash().value >= massTaskDetail.getBudget().value){
            r1 = requestorMassTaskData.saveMassTaskDetail(massTaskDetail);
            r2 = manageUserBL.reduceCash(requestorId, massTaskDetail.getBudget());
        }else {
            return new CashNotEnoughException();
        }

        if (r1 && r2){
            return new SuccessException();
        }else {
            return new FailureException();
        }
    }

    @Override
    public List<PublishedMassTaskVO> getPublishedMassTaskVOList(UserId requestorId) {
        List<PublishedMassTaskVO> publishedMassTaskVOList = new ArrayList<>();

        List<MassTaskDetail> massTaskDetails = requestorMassTaskData.getAllMassTaskDetailOfThisRequestor(requestorId);
        for (MassTaskDetail massTaskDetail: massTaskDetails){
            PublishedTask tempPublishedTask = requestorData.getPublishedTask(massTaskDetail.getTaskId());

            publishedMassTaskVOList.add(new PublishedMassTaskVO(
                    new PublishedTaskVO(tempPublishedTask), new MassTaskDetailVO(massTaskDetail)
            ));
        }
        return publishedMassTaskVOList;
    }
}
