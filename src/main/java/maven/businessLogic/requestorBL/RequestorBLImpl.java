package maven.businessLogic.requestorBL;

import maven.businessLogic.algorithm.LabelQualityVerifier;
import maven.businessLogic.algorithm.PrestigeAlgorithm;
import maven.businessLogic.manageUserBL.ManageUserBLImpl;
import maven.businessLogic.manageUserBL.ManageUserBLService;
import maven.data.Map.MapDataImpl;
import maven.data.Map.MapDataService;
import maven.data.MessageData.MessageDataImpl;
import maven.data.MessageData.MessageDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.exception.AssignException.*;
import maven.exception.util.*;
import maven.model.message.AcceptedTaskMessage;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Requestor;
import maven.model.user.Worker;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RequestorBLImpl implements RequestorBLService{

    private RequestorDataService requestorDataService;
    private WorkerDataService workerDataService;
    private MapDataService mapDataService;
    private MessageDataService messageDataService;
    private ManageUserBLService manageUserBLService;
    private LabelQualityVerifier labelQualityVerifier;
    private PrestigeAlgorithm prestigeAlgorithm;

    public RequestorBLImpl(){
        requestorDataService = new RequestorDataImpl();
        workerDataService = new WorkerDataImpl();
        mapDataService = new MapDataImpl();
        messageDataService = new MessageDataImpl();
        manageUserBLService = new ManageUserBLImpl();
        labelQualityVerifier = new LabelQualityVerifier();
        prestigeAlgorithm = new PrestigeAlgorithm();
    }

    @Override
    public Map<TaskType, Cash> getTaskUnitPriceMap() {
        return mapDataService.getCashTaskType();
    }

    @Override
    public Exception uploadTaskInfo(PublishedTaskVO publishedTaskVO, List<Filename> imageFilenameList) {

        TaskId taskId = new TaskId(publishedTaskVO.getTaskId());
        UserId userId = getUserIdFromTaskId(taskId);

        PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(new WorkerNum(publishedTaskVO.getRequiredWorkerNum()), new Cash(publishedTaskVO.getTaskPrice()), null);

        List<PublishedTaskDetail> publishedTaskDetailList = new ArrayList<>();
        publishedTaskDetailList.add(publishedTaskDetail);

        PublishedTask publishedTask = new PublishedTask(taskId, userId, TaskType.valueOf(publishedTaskVO.getLabelType()), new LabelType(publishedTaskVO.getLabelType()), imageFilenameList,
                new TaskDescription(publishedTaskVO.getTaskDescription()), new WorkerNum(0), new WorkerNum(0),
                publishedTaskDetailList, PublishedTaskState.DRAFT_WITHOUT_SAMPLE
                );

        //下面生成样本数据

        //原本任务图片集的图片总数
        int imageNum = imageFilenameList.size();
        //样本内图片数量
        int sampleImageNum;
        //样本图片在原本任务图片集内的下标数组
        List<Integer> imageIndexList = new ArrayList<>();

        if(imageNum < 5)
            sampleImageNum = 1;
        else if(imageNum < 50)
            sampleImageNum = imageNum/5;
        else
            sampleImageNum = 10;

        int temp;
        boolean isFound;
        while(imageIndexList.size() < sampleImageNum){
            temp = (int)(Math.random()*imageNum);
            isFound = false;
            for(Integer index : imageIndexList){
                if(index == temp)
                    isFound = true;
            }
            if(!isFound)
                imageIndexList.add(temp);
        }
        //将imageIndexList 从小到大 进行排序
        imageIndexList.sort(Comparator.comparing(Integer::intValue));

        if(requestorDataService.saveTaskInfo(publishedTask) && requestorDataService.saveTaskSampleInfo(taskId, sampleImageNum, imageIndexList))
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
        Requestor requestor = (Requestor)manageUserBLService.getUserByUserId(userId);
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        Cash taskPrice = publishedTask.getTaskPrice();
        WorkerNum requiredWorkerNum = publishedTask.getRequiredWorkerNum();

        //若该任务仍处于 未标注样本的草稿状态
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
    public Exception terminateTask(TaskId taskId) {

        //获取该任务的详情
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);

        //若当前任务状态 并非 正在进行中
        if(publishedTask.getPublishedTaskState() != PublishedTaskState.INCOMPLETE)
            return new FailureException();

        //发布者为该任务支付的全部金额
        double paidCashByRequestor = publishedTask.getTaskPrice().value * publishedTask.getRequiredWorkerNum().value;
        //为已经完成并通过审核的工人支付的金额
        double paidForWorker = 0;


        //获取当前已接受该任务的工人任务完成情况
        List<AcceptedTask> last_acceptedTaskList = workerDataService.getAcceptedTaskList(taskId);
        for(AcceptedTask acceptedTask : last_acceptedTaskList){
            //将已完成并待审核的工人任务 通过审核
            if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.SUBMITTED){
                if(passTask(acceptedTask.getTaskId(), acceptedTask.getUserId()) instanceof FailureException)
                    return new FailureException();
            }
            //将已接受但未完成的工人任务 设为结束状态
            if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.ACCEPTED){
                if(!workerDataService.reviseAcceptedTaskState(acceptedTask.getUserId(), acceptedTask.getTaskId(), AcceptedTaskState.TERMINATED))
                    return new FailureException();
            }
        }

        //重新获取列表 计算为工人支付的金额
        List<AcceptedTask> acceptedTaskList = workerDataService.getAcceptedTaskList(taskId);
        for(AcceptedTask acceptedTask : acceptedTaskList) {
            if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.PASSED){
                paidForWorker += acceptedTask.getOriginalTaskPrice().value;
            }
        }


        //获取发布者Id
        UserId userId = getUserIdFromTaskId(taskId);
        //应该返还给发布者的金额
        Cash returnCash = new Cash(paidCashByRequestor - paidForWorker);
        if(requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.TERMINATED)
                && manageUserBLService.increaseCash(userId, returnCash))
            return new SuccessException();

        return new FailureException();
    }

    @Override
    public Exception reviseTaskPrice(TaskId taskId, Cash cash) {
        PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
        UserId userId = getUserIdFromTaskId(taskId);
        Requestor requestor = (Requestor)manageUserBLService.getUserByUserId(userId);

        if(publishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE){
            WorkerNum lastWorkerNum = publishedTask.getRequiredWorkerNum();
            Cash lastTaskPrice = publishedTask.getTaskPrice();

            if(requestor.getCash().value < (cash.value - lastTaskPrice.value)*lastWorkerNum.value)
                return new CashNotEnoughException();
            else{

                //扣除金额
                if(!manageUserBLService.reduceCash(userId, new Cash((cash.value - lastTaskPrice.value)*lastWorkerNum.value)))
                    return new FailureException();

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
        Requestor requestor = (Requestor)manageUserBLService.getUserByUserId(userId);

        if(publishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE){
            WorkerNum lastWorkerNum = publishedTask.getRequiredWorkerNum();
            Cash lastTaskPrice = publishedTask.getTaskPrice();

            if(requestor.getCash().value < lastTaskPrice.value*(workerNum.value-lastWorkerNum.value))
                return new CashNotEnoughException();
            else{

                //扣除金额
                if(!manageUserBLService.reduceCash(userId, new Cash(lastTaskPrice.value*(workerNum.value-lastWorkerNum.value))))
                    return new FailureException();

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
        List<AcceptedTask> acceptedTaskList = workerDataService.getAcceptedTaskList(taskId);
        
        PublishedTask publishedTask;
        Username username;
        LabelType labelType;
        TaskDescription taskDescription;
        //查找工人已完成并待审核的任务
        for(AcceptedTask acceptedTask : acceptedTaskList){
            if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.SUBMITTED) {
                publishedTask = requestorDataService.getPublishedTask(taskId);
                username = manageUserBLService.getUserByUserId(acceptedTask.getUserId()).getUsername();
                labelType = publishedTask.getLabelType();
                taskDescription = publishedTask.getTaskDescription();
                list.add(new AcceptedTaskVO(acceptedTask, username, labelType, taskDescription));
            }
        }
        
        return list;
    }

    @Override
    public Exception passTask(TaskId taskId, UserId userId) {
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.PASSED)){
            Worker worker = (Worker) manageUserBLService.getUserByUserId(userId);
            TaskType taskType = requestorDataService.getTaskType(taskId);
            Cash priceOfTask = workerDataService.getAcceptedTaskById(userId, taskId).getActualTaskPrice();

            //通过审核后，给工人发奖励
            manageUserBLService.increaseCash(userId, priceOfTask);
            manageUserBLService.revisePrestige(userId, prestigeAlgorithm.renewWorkerPrestige(worker.getPrestige(), LabelQuality.TRUSTFUL, taskType));

            /**
             * 若发布者及时对工人进行审核，则增长发布者声望
             */


            //生成给工人的任务通过审核的消息
            AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, taskId, priceOfTask, AcceptedTaskState.PASSED);
            messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);


            PublishedTask publishedTask = requestorDataService.getPublishedTask(taskId);
            //判断该任务是否已完成
            if(publishedTask.getFinishedWorkerNum().value == publishedTask.getRequiredWorkerNum().value - 1)
                requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.ACCOMPLISHED);
            //修改发布任务的已完成人数
            requestorDataService.reviseTaskFinishedWorkerNum(taskId, new WorkerNum(publishedTask.getFinishedWorkerNum().value+1));

            return new SuccessException();
        }
        else
            return new FailureException();
    }

    @Override
    public Exception rejectTask(TaskId taskId, UserId userId) {
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.REJECTED)){

            Cash priceOfTask = workerDataService.getAcceptedTaskById(userId, taskId).getActualTaskPrice();

            //生成给工人的任务被驳回的消息
            AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, taskId, priceOfTask, AcceptedTaskState.REJECTED);
            messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);

            //扣除工人声望
            manageUserBLService.reducePrestige(userId, new Prestige(0.5));

            /**
             * 若发布者及时对工人进行审核，则增长发布者声望
             */

            return new SuccessException();
        }
        else
            return new FailureException();
    }

    @Override
    public Exception abandonTaskByRequestor(TaskId taskId, UserId userId) {
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.ABANDONED_BY_REQUESTOR)){
            Worker worker = (Worker) manageUserBLService.getUserByUserId(userId);
            TaskType taskType = requestorDataService.getTaskType(taskId);

            Cash priceOfTask = workerDataService.getAcceptedTaskById(userId, taskId).getActualTaskPrice();

            //生成给工人的任务被废弃的消息
            AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, taskId, priceOfTask, AcceptedTaskState.ABANDONED_BY_REQUESTOR);
            messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);

            UserId requestorId = getUserIdFromTaskId(taskId);

            //判断发布者作出的评价是否有效（即无恶意）
            if(labelQualityVerifier.isLabelQualityValid(requestorId, userId, LabelQuality.DISTRUSTFUL)){
                //若为非恶意评价，则扣除工人的声望
                manageUserBLService.revisePrestige(userId, prestigeAlgorithm.renewWorkerPrestige(worker.getPrestige(), LabelQuality.DISTRUSTFUL, taskType));
            }else {
                //若为恶意评价，则扣除发布者的声望
                manageUserBLService.reducePrestige(requestorId, new Prestige(1));
            }

            /**
             * 若发布者及时对工人进行审核，则增长发布者声望
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

        List<AcceptedTask> acceptedTaskList = workerDataService.getAcceptedTaskList(taskId);
        List<AcceptedTaskVO> list = new ArrayList<>();
        Username username;
        for(AcceptedTask acceptedTask : acceptedTaskList) {
            username = manageUserBLService.getUserByUserId(acceptedTask.getUserId()).getUsername();
            list.add(new AcceptedTaskVO(acceptedTask, username, labelType, taskDescription));
        }
        return list;
    }


    private UserId getUserIdFromTaskId(TaskId taskId) {
        String Task_Id = taskId.value;
        String[] temp = Task_Id.split("_");
        String User_Id = temp[0];
//        System.out.println(User_Id);
        return new UserId(User_Id);
    }
}
