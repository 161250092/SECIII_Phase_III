package maven.businessLogic.requestorBL;

import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.exception.AssignException.*;
import maven.exception.util.*;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Requestor;
import maven.model.user.User;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.ArrayList;
import java.util.List;

public class RequestorBLImpl implements RequestorBLService{

    private RequestorDataService requestorDataService;
    private WorkerDataService workerDataService;
    private UserDataService userDataService;

    public RequestorBLImpl(){
        requestorDataService = new RequestorDataImpl();
        workerDataService = new WorkerDataImpl();
        userDataService = new UserDataImpl();
    }

    @Override
    public Exception uploadTaskInfo(PublishedTaskVO publishedTaskVO, List<Filename> imageFilenameList) {

        TaskId taskId = new TaskId(publishedTaskVO.getTaskId());
        UserId userId = getUserIdFromTaskId(taskId);

        PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(new WorkerNum(publishedTaskVO.getRequiredWorkerNum()), new Cash(publishedTaskVO.getTaskPrice()), null);

        List<PublishedTaskDetail> publishedTaskDetailList = new ArrayList<>();
        publishedTaskDetailList.add(publishedTaskDetail);

        PublishedTask publishedTask = new PublishedTask(taskId, userId, new LabelType(publishedTaskVO.getLabelType()), imageFilenameList,
                new TaskDescription(publishedTaskVO.getTaskDescription()), new WorkerNum(0), new WorkerNum(0),
                publishedTaskDetailList, PublishedTaskState.DRAFT_WITHOUT_SAMPLE
                );
        if(requestorDataService.saveTaskInfo(publishedTask))
            return new SuccessException();
        else
            return new FailureException();
    }

    @Override
    public List<PublishedTaskVO> getTaskDraftList(UserId userId) {
        List<PublishedTaskVO> publishedTaskVOList = new ArrayList<>();
        List<PublishedTask> publishedTaskList = requestorDataService.getPublishedTaskList(userId);
        PublishedTaskVO vo;
        //查找状态为 未标注样本的草稿 或 已标注样本的草稿 的任务
        for(PublishedTask publishedTask : publishedTaskList){
            if(publishedTask.getPublishedTaskState()==PublishedTaskState.DRAFT_WITHOUT_SAMPLE
                     || publishedTask.getPublishedTaskState()==PublishedTaskState.DRAFT_WITH_SAMPLE){
                vo = new PublishedTaskVO(publishedTask);
                publishedTaskVOList.add(vo);
            }
        }
        return publishedTaskVOList;
    }

    @Override
    public Exception assignTask(TaskId taskId) {
        UserId userId = getUserIdFromTaskId(taskId);
        Requestor requestor = (Requestor)userDataService.getUserByUserId(userId);
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        Cash taskPrice = publishedTask.getTaskPrice();
        WorkerNum requiredWorkerNum = publishedTask.getRequiredWorkerNum();

        //若该任务仍处于 未注样本的草稿状态
        if(publishedTask.getPublishedTaskState() == PublishedTaskState.DRAFT_WITHOUT_SAMPLE)
            return new IncompleteSampleException();
        else{
            //若用户权限不足，已发布且正在进行中的任务数 达到最大值时，不允许发布
            if(getAssignedButIncompleteTaskList(userId).size() ==  requestor.getMaxPublishedTaskNum().value ){
                return new PrestigeNotEnoughException();
            }
            else {
                if(requestor.getCash().value >= taskPrice.value * requiredWorkerNum.value) {
                    //将该任务状态修改为 正在进行中（未完成）
                    if(requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.INCOMPLETE)) {

                        /**
                         * 增长用户的声望
                         */

                        return new AssignSuccessException();
                    }
                }
                else
                    return new CashNotEnoughException();
            }
        }
            return new FailureException();
    }

    @Override
    public Exception revokeTask(TaskId taskId) {

        /**
         * 金额返还
         *
         * 未完成
         *
         */
        if(requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.REVOKED)){
            
            
            return new SuccessException();            
        }


        return new FailureException();
    }

    @Override
    public Exception reviseTaskPrice(TaskId taskId, Cash cash) {
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        UserId userId = getUserIdFromTaskId(taskId);
        Requestor requestor = (Requestor)userDataService.getUserByUserId(userId);

        if(publishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE){
            WorkerNum lastWorkerNum = publishedTask.getRequiredWorkerNum();
            Cash lastTaskPrice = publishedTask.getTaskPrice();

            if(requestor.getCash().value < (lastTaskPrice.value - cash.value)*lastWorkerNum.value)
                return new CashNotEnoughException();
            else{

                //扣除金额
                double currentCash = requestor.getCash().value -(lastTaskPrice.value - cash.value)*lastWorkerNum.value;
                userDataService.reviseCash(userId, new Cash(currentCash));

                /**
                 * 修改声望 权限
                 */

                PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(lastWorkerNum, cash, null );
                if(requestorDataService.saveTaskDetail(taskId, publishedTaskDetail))
                    return new SuccessException();
            }
        }
        return new FailureException();
    }

    @Override
    public Exception reviseTaskRequiredNum(TaskId taskId, WorkerNum workerNum) {
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        UserId userId = getUserIdFromTaskId(taskId);
        Requestor requestor = (Requestor)userDataService.getUserByUserId(userId);

        if(publishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE){
            WorkerNum lastWorkerNum = publishedTask.getRequiredWorkerNum();
            Cash lastTaskPrice = publishedTask.getTaskPrice();

            if(requestor.getCash().value < lastTaskPrice.value*(workerNum.value-lastWorkerNum.value))
                return new CashNotEnoughException();
            else{

                //扣除金额
                double currentCash = requestor.getCash().value -lastTaskPrice.value*(workerNum.value-lastWorkerNum.value);
                userDataService.reviseCash(userId, new Cash(currentCash));

                /**
                 * 修改声望 权限
                 */

                PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(workerNum, lastTaskPrice, null );
                if(requestorDataService.saveTaskDetail(taskId, publishedTaskDetail))
                    return new SuccessException();
            }
        }
        return new FailureException();
    }


    @Override
    public List<AcceptedTaskVO> getSubmittedTaskList(TaskId taskId, UserId userId) {
        List<AcceptedTaskVO> list = new ArrayList<>();
        List<AcceptedTask> acceptedTaskList = requestorDataService.getAcceptedTaskList(taskId);
        
        PublishedTask publishedTask;
        Username username;
        LabelType labelType;
        TaskDescription taskDescription;
        //查找工人已完成并待审核的任务
        for(AcceptedTask acceptedTask : acceptedTaskList){
            if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.SUBMITTED) {
                publishedTask = requestorDataService.getPublishedTask(taskId);
                username = getUsernameByUserId(acceptedTask.getUserId());
                labelType = publishedTask.getLabelType();
                taskDescription = publishedTask.getTaskDescription();
                list.add(new AcceptedTaskVO(acceptedTask, username, labelType, taskDescription));
            }
        }
        
        return list;
    }

    @Override
    public Exception passTask(TaskId taskId, UserId userId) {
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.ACCEPTED)){
            User user = userDataService.getUserByUserId(userId);
            Cash cash = user.getCash();
            Cash priceOfTask = workerDataService.getAcceptedTaskById(userId, taskId).getActualTaskPrice();
            userDataService.reviseCash(userId, new Cash(cash.value + priceOfTask.value));

            /**
             * 修改工人的声望
             */

            return new SuccessException();
        }
        else
            return new FailureException();
    }

    @Override
    public Exception rejectTask(TaskId taskId, UserId userId) {
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.REJECTED))
            return new SuccessException();
        else
            return new FailureException();
    }

    @Override
    public Exception abandonTaskByRequestor(TaskId taskId, UserId userId) {
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.ABANDONED_BY_REQUESTOR)){

            /**
             * 修改工人的声望
             */

            return new SuccessException();
        }
        else
            return new FailureException();
    }

    @Override
    public List<PublishedTaskVO> getAssignedAndAccomplishedTaskList(UserId userId) {
        List<PublishedTaskVO> list = new ArrayList<>();
        List<PublishedTask> publishedTaskList = requestorDataService.getPublishedTaskList(userId);
        for(PublishedTask publishedTask : publishedTaskList){
            if(publishedTask.getPublishedTaskState() == PublishedTaskState.ACCOMPLISHED)
                list.add(new PublishedTaskVO(publishedTask));
        }
        return list;
    }

    @Override
    public List<PublishedTaskVO> getAssignedButIncompleteTaskList(UserId userId) {
        List<PublishedTaskVO> list = new ArrayList<>();
        List<PublishedTask> publishedTaskList = requestorDataService.getPublishedTaskList(userId);
        for(PublishedTask publishedTask : publishedTaskList){
            if(publishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE)
                list.add(new PublishedTaskVO(publishedTask));
        }
        return list;
    }

    @Override
    public PublishedTask getAssignedTask(UserId userId, TaskId taskId) {
        return requestorDataService.getPublishedTask(taskId);
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedTaskVOList(UserId userId, TaskId taskId) {
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        if(publishedTask == null)
            return null;
        LabelType labelType = publishedTask.getLabelType();
        TaskDescription taskDescription = publishedTask.getTaskDescription();

        List<AcceptedTask> acceptedTaskList = requestorDataService.getAcceptedTaskList(taskId);
        List<AcceptedTaskVO> list = new ArrayList<>();
        Username username;
        for(AcceptedTask acceptedTask : acceptedTaskList) {
            username = getUsernameByUserId(acceptedTask.getUserId());
            list.add(new AcceptedTaskVO(acceptedTask, username, labelType, taskDescription));
        }
        return list;
    }


    private UserId getUserIdFromTaskId(TaskId taskId){
        String Task_Id = taskId.value;
        String[] temp = Task_Id.split("_");
        String User_Id = temp[0];
        System.out.println(User_Id);
        return new UserId(User_Id);
    }

    
    private Username getUsernameByUserId(UserId userId){
        User user = userDataService.getUserByUserId(userId);
        if(user == null)
            return null;
        else
            return user.getUsername();
    }
}
