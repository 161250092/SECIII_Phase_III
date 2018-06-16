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
import maven.model.message.BillMessage;
import maven.model.message.BillReason;
import maven.model.message.BillType;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.Requestor;
import maven.model.user.Worker;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.*;

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

        PublishedTask publishedTask = new PublishedTask(taskId, userId, TaskType.valueOf(publishedTaskVO.getTaskType()), new LabelType(publishedTaskVO.getLabelType()), imageFilenameList,
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

        //生成样本
        Sample sample = new Sample(taskId, sampleImageNum, imageIndexList);

        //保存发布任务信息、保存样本数据
        if(requestorDataService.saveTaskInfo(publishedTask)
                && requestorDataService.saveTaskSampleInfo(sample))
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
                if(requestor.getCash().value < taskPrice.value * requiredWorkerNum.value)
                    return new CashNotEnoughException();
                else{
                    //发布者需要支付的金额
                    Cash payment = new Cash(taskPrice.value * requiredWorkerNum.value);

                    //从发布者的账户里扣除金额
                    if(!manageUserBLService.reduceCash(userId, payment))
                        return new FailureException();

                    //将该任务状态修改为 正在进行中（未完成）
                    requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.INCOMPLETE);

                    //生成账单消息，提醒发布者 因发布任务而支出
                    BillMessage billMessage = new BillMessage(messageDataService.getMessageIdForCreateMessage(),
                            userId, BillType.OUT, BillReason.ASSIGN_TASK, payment);
                    messageDataService.saveBillMessage(billMessage);

                    return new AssignSuccessException();
                }

            }
        }
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
            //将已接受但未完成的工人任务 设为终止状态
            if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.ACCEPTED){
                if(!workerDataService.reviseAcceptedTaskState(acceptedTask.getUserId(), acceptedTask.getTaskId(), AcceptedTaskState.TERMINATED))
                    return new FailureException();

                //生成工人任务消息，提醒工人 任务被发布者终止
                AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(), acceptedTask.getUserId(),
                        taskId, acceptedTask.getActualTaskPrice(), AcceptedTaskState.TERMINATED);
                messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);
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

        //保存账单信息
        BillMessage billMessage = new BillMessage(messageDataService.getMessageIdForCreateMessage(), userId,
                BillType.IN, BillReason.TERMINATE_TASK, returnCash);
        messageDataService.saveBillMessage(billMessage);

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
                //发布者需要支付的金额
                Cash payment = new Cash((cash.value - lastTaskPrice.value)*lastWorkerNum.value);

                //扣除金额
                if(!manageUserBLService.reduceCash(userId, payment))
                    return new FailureException();

                //更新发布的任务
                PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(lastWorkerNum, cash, null );
                requestorDataService.saveTaskDetail(taskId, publishedTaskDetail);

                //生成账单消息，提醒发布者 因追加任务金额而支出
                BillMessage billMessage = new BillMessage(messageDataService.getMessageIdForCreateMessage(),
                            userId, BillType.OUT, BillReason.SUPPLEMENT_TASK_CASH, payment);
                messageDataService.saveBillMessage(billMessage);

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
                //发布者需要支付的金额
                Cash payment = new Cash(lastTaskPrice.value*(workerNum.value-lastWorkerNum.value));

                //扣除金额
                if(!manageUserBLService.reduceCash(userId, payment))
                    return new FailureException();

                //更新发布的任务
                PublishedTaskDetail publishedTaskDetail = new PublishedTaskDetail(workerNum, lastTaskPrice, null );
                requestorDataService.saveTaskDetail(taskId, publishedTaskDetail);

                //生成账单消息，提醒发布者 因追加任务需求人数而支出
                BillMessage billMessage = new BillMessage(messageDataService.getMessageIdForCreateMessage(),
                        userId, BillType.OUT, BillReason.SUPPLEMENT_TASK_REQUIRED_NUM, payment);
                messageDataService.saveBillMessage(billMessage);

                return new SuccessException();
            }
        }
        return new FailureException();
    }


    @Override
    public List<AcceptedTaskVO> getSubmittedTaskList(TaskId taskId) {
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
            AcceptedTask acceptedTask = workerDataService.getAcceptedTaskById(userId, taskId);
            Cash priceOfTask = acceptedTask.getActualTaskPrice();

            //通过审核后，给工人发奖励
            manageUserBLService.increaseCash(userId, priceOfTask);
            manageUserBLService.revisePrestige(userId, prestigeAlgorithm.renewWorkerPrestige(worker.getPrestige(), LabelQuality.TRUSTFUL, taskType));

            // 若发布者及时对工人进行审核，则增长发布者声望
            if(isApprovedDuly(new Date(), acceptedTask.getStartTime()))
                manageUserBLService.increasePrestige(getUserIdFromTaskId(taskId), new Prestige(0.2));


            //生成工人任务消息，提醒工人 任务通过审核
            AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, taskId, priceOfTask, AcceptedTaskState.PASSED);
            messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);

            //生成账单消息，提醒工人 获得赏金
            BillMessage billMessage = new BillMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, BillType.IN, BillReason.ACCOMPLISH_TASK, priceOfTask);
            messageDataService.saveBillMessage(billMessage);


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
            AcceptedTask acceptedTask = workerDataService.getAcceptedTaskById(userId, taskId);
            Cash priceOfTask = acceptedTask.getActualTaskPrice();

            //生成工人任务消息，提醒工人 任务被驳回
            AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, taskId, priceOfTask, AcceptedTaskState.REJECTED);
            messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);

//            //扣除工人声望
//            manageUserBLService.reducePrestige(userId, new Prestige(0.5));

            // 若发布者及时对工人进行审核，则增长发布者声望
            if(isApprovedDuly(new Date(), acceptedTask.getStartTime()))
                manageUserBLService.increasePrestige(getUserIdFromTaskId(taskId), new Prestige(0.2));

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
            AcceptedTask acceptedTask = workerDataService.getAcceptedTaskById(userId, taskId);
            Cash priceOfTask = acceptedTask.getActualTaskPrice();

            //生成工人任务消息，提醒工人 任务被废弃
            AcceptedTaskMessage acceptedTaskMessage = new AcceptedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    userId, taskId, priceOfTask, AcceptedTaskState.ABANDONED_BY_REQUESTOR);
            messageDataService.saveAcceptedTaskMessage(acceptedTaskMessage);

            UserId requestorId = getUserIdFromTaskId(taskId);

            //判断发布者作出的评价是否有效（即无恶意）
            if(labelQualityVerifier.isLabelQualityValid(requestorId, userId, LabelQuality.DISTRUSTFUL)){
                //若为非恶意评价，则扣除工人的声望
                manageUserBLService.revisePrestige(userId, prestigeAlgorithm.renewWorkerPrestige(worker.getPrestige(), LabelQuality.DISTRUSTFUL, taskType));
                // 若发布者及时对工人进行审核，则增长发布者声望
                if(isApprovedDuly(new Date(), acceptedTask.getStartTime()))
                    manageUserBLService.increasePrestige(getUserIdFromTaskId(taskId), new Prestige(0.2));
            }else {
                //若为恶意评价，则扣除发布者的声望
                manageUserBLService.reducePrestige(requestorId, new Prestige(1));
            }

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

    @Override
    public boolean charge(UserId userId, Cash cash) {
        //生成账单消息 因工人提现而扣除金额
        BillMessage billMessage = new BillMessage(messageDataService.getMessageIdForCreateMessage(), userId,
                BillType.IN, BillReason.CHARGE, cash);
        messageDataService.saveBillMessage(billMessage);
        return manageUserBLService.increaseCash(userId,cash);
    }


    private UserId getUserIdFromTaskId(TaskId taskId) {
        String Task_Id = taskId.value;
        String[] temp = Task_Id.split("_");
        String User_Id = temp[0];
//        System.out.println(User_Id);
        return new UserId(User_Id);
    }

    //判断发布者是否及时审核
    private boolean isApprovedDuly(Date approveTime, Date arriveTime){
        Calendar calendar_1 = new GregorianCalendar();
        Calendar calendar_2 = new GregorianCalendar();
        calendar_1.setTime(approveTime);
        calendar_2.setTime(arriveTime);
        //把日期向后推一天
        calendar_2.add(calendar_1.DATE,1);

        //判断发布者是否 在工人提交任务后的一天之内 完成审核的
        return calendar_1.before(calendar_2);
    }

}
