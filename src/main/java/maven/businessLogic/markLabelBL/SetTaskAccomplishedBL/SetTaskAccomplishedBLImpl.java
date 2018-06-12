package maven.businessLogic.markLabelBL.SetTaskAccomplishedBL;

import maven.businessLogic.evaluateBL.EvaluateLabelBLImpl;
import maven.businessLogic.evaluateBL.EvaluateLabelBLService;
import maven.data.MessageData.MessageDataImpl;
import maven.data.MessageData.MessageDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.model.message.PublishedTaskMessage;
import maven.model.primitiveType.Cash;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.primitiveType.Username;
import maven.model.task.AcceptedTask;
import maven.model.task.AcceptedTaskState;
import maven.model.task.PublishedTaskState;
import maven.model.user.User;

public class SetTaskAccomplishedBLImpl implements SetTaskAccomplishedBLService{
    private RequestorDataService requestorDataService;
    private WorkerDataService workerDataService;
    private UserDataService userDataService;
    private MessageDataService messageDataService;
    private EvaluateLabelBLService evaluateLabelBLService;

    public SetTaskAccomplishedBLImpl(){
        requestorDataService = new RequestorDataImpl();
        workerDataService = new WorkerDataImpl();
        userDataService = new UserDataImpl();
        messageDataService = new MessageDataImpl();
        evaluateLabelBLService = new EvaluateLabelBLImpl();
    }

    @Override
    public boolean setTaskAccomplished(TaskId taskId, UserId userId) {
        String[] temp = taskId.value.split("_");
        String userIdFromTaskId = temp[0];
        //若完成标注的是发布者，修改发布者任务的状态---已标注样本的草稿
        if(userId.value.equals(userIdFromTaskId))
            return  requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.DRAFT_WITH_SAMPLE);
        //若完成标注的是工人，修改工人任务的状态
        else{
            UserId requestorId = new UserId(userIdFromTaskId);
            AcceptedTask acceptedTask = workerDataService.getAcceptedTaskById(userId, taskId);
            User worker = userDataService.getUserByUserId(userId);
            Username workername = worker.getUsername();
            Cash cash = acceptedTask.getOriginalTaskPrice();

            //生成发布任务消息 提醒发布者审核任务
            PublishedTaskMessage publishedTaskMessage = new PublishedTaskMessage(messageDataService.getMessageIdForCreateMessage(),
                    requestorId, taskId, userId, workername, cash);
            messageDataService.savePublishedTaskMessage(publishedTaskMessage);

            //修改工人任务的状态 并对工人的标注进行评分
            return workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.SUBMITTED)
                    && evaluateLabelBLService.evaluateLabel(taskId, userId);
        }

    }
}
