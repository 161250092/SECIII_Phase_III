package maven.businessLogic.requestorBL;

import maven.data.RequestorData.RequestorDataService;
import maven.data.RequestorData.RequestorMassTaskDataService;
import maven.exception.util.FailureException;
import maven.exception.util.SuccessException;
import maven.model.massTask.MassTaskDetail;
import maven.model.primitiveType.UserId;
import maven.model.task.PublishedTask;
import maven.model.vo.MassTaskDetailVO;
import maven.model.vo.PublishedMassTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.ArrayList;
import java.util.List;

public class RequestorMassTaskBLImpl implements RequestorMassTaskBLService{

    private RequestorMassTaskDataService requestorMassTaskData;
    private RequestorDataService requestorData;

    @Override
    public Exception uploadMassTaskDetail(MassTaskDetail massTaskDetail) {
        if (requestorMassTaskData.saveMassTaskDetail(massTaskDetail)){
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
