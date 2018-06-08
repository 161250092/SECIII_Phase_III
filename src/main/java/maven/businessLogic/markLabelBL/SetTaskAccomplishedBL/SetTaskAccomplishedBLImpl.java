package maven.businessLogic.markLabelBL.SetTaskAccomplishedBL;

import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTaskState;
import maven.model.task.PublishedTaskState;

public class SetTaskAccomplishedBLImpl implements SetTaskAccomplishedBLService{
    private RequestorDataService requestorDataService;
    private WorkerDataService workerDataService;

    public SetTaskAccomplishedBLImpl(){
        requestorDataService = new RequestorDataImpl();
        workerDataService = new WorkerDataImpl();
    }

    @Override
    public boolean setTaskAccomplished(TaskId taskId, UserId userId) {
        String[] temp = taskId.value.split("_");
        String userIdFromTaskId = temp[0];
        //若完成标注的是发布者，修改发布者任务的状态---已标注样本的草稿
        if(userId.value.equals(userIdFromTaskId))
            return  requestorDataService.revisePublishedTaskState(taskId, PublishedTaskState.DRAFT_WITH_SAMPLE);
        //若完成标注的是工人，修改工人任务的状态
        else
            return workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.SUBMITTED);
    }
}
