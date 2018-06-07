package maven.businessLogic.workerBL;

import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataImpl;
import maven.data.MarkLabelData.AreaLabelData.AreaLabelDataService;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataImpl;
import maven.data.MarkLabelData.FrameLabelData.FrameLabelDataService;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataImpl;
import maven.data.MarkLabelData.ImageLabelData.ImageLabelDataService;
import maven.data.RequestorData.RequestorDataImpl;
import maven.data.RequestorData.RequestorDataService;
import maven.data.UserData.UserDataImpl;
import maven.data.UserData.UserDataService;
import maven.data.WorkerData.WorkerDataImpl;
import maven.data.WorkerData.WorkerDataService;
import maven.exception.DataException.TaskNotFoundException;
import maven.exception.util.FailureException;
import maven.exception.util.SuccessException;
import maven.model.primitiveType.*;
import maven.model.task.*;
import maven.model.user.*;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;

import java.util.*;

public class WorkerBLImpl implements WorkerBLService {
    private WorkerDataService workerDataService;
    private RequestorDataService requestorDataService;
    private UserDataService userDataService;

    public WorkerBLImpl(){
        workerDataService = new WorkerDataImpl();
        requestorDataService = new RequestorDataImpl();
        userDataService = new UserDataImpl();
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId) {
        List<AcceptedTaskVO> list = new ArrayList<>();
        List<AcceptedTaskVO> allAcceptedTaskVOList = getAcceptedTaskVOList(userId);
        for(AcceptedTaskVO vo : allAcceptedTaskVOList){
            //查找状态为 完成、通过、驳回、被发布者废弃 的任务
            if(!vo.getAcceptedTaskState().equals(AcceptedTaskState.ACCEPTED.toString())
                    && !vo.getAcceptedTaskState().equals(AcceptedTaskState.ABANDONED_BY_WORKER.toString()) ){
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(UserId userId) {
        List<AcceptedTaskVO> list = new ArrayList<>();
        List<AcceptedTaskVO> allAcceptedTaskVOList = getAcceptedTaskVOList(userId);
        for(AcceptedTaskVO vo : allAcceptedTaskVOList){
            //查找状态为 接受 的任务
            if(vo.getAcceptedTaskState().equals(AcceptedTaskState.ACCEPTED.toString())){
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public List<PublishedTaskVO> getAvailableTaskList(UserId userId) {
        List<PublishedTaskVO> list = new ArrayList<>();

        List<Requestor> requestorList = userDataService.getAllRequestor();
        List<PublishedTask> requestorPublishedTaskList;
        for(Requestor requestor : requestorList){
            //获取该发布者所有的任务
            requestorPublishedTaskList = requestorDataService.getPublishedTaskList(requestor.getUserId());
            for(PublishedTask publishedTask : requestorPublishedTaskList){
                //仅当该任务仍在进行中 且接受人数<需求人数时，该任务可被接受
                if(publishedTask.getPublishedTaskState() == PublishedTaskState.INCOMPLETE
                        && publishedTask.getAcceptedWorkerNum().value < publishedTask.getRequiredWorkerNum().value){
                    //判断工人是否可以接受某任务（排除掉 以往接受过的情况）
                    if(havePermissionToAcceptTask(userId, publishedTask.getTaskId()))
                        list.add(new PublishedTaskVO(publishedTask));
                }
            }
        }
        return list;
    }

    @Override
    public Exception acceptTask(UserId userId, List<TaskId> taskIdList) {
        PublishedTask publishedTask;
        AcceptedTask acceptedTask;
        for(TaskId taskId : taskIdList){
            //查找发布者已发布的任务 并获取信息
            publishedTask = requestorDataService.getPublishedTask(taskId);
            if(publishedTask == null)
                return new TaskNotFoundException();
            acceptedTask = new AcceptedTask(userId, publishedTask.getTaskId(), new Date(), publishedTask.getTaskPrice(),
                    null, AcceptedTaskState.ACCEPTED, new LabelScore(-1));
            if(workerDataService.acceptTask(acceptedTask))
                return new FailureException();
        }
        return new SuccessException();
    }

    @Override
    public Exception abandonTaskByWorker(UserId userId, TaskId taskId) {
        AcceptedTask acceptedTask = workerDataService.getAcceptedTaskById(userId, taskId);
        if(acceptedTask == null)
            return new TaskNotFoundException();
        //若任务已完成 则不允许废弃
        if(acceptedTask.getAcceptedTaskState() == AcceptedTaskState.PASSED)
            return new FailureException();

        String[] temp = taskId.value.split("_");
        String labelType = temp[1];

        //删除已有的标注信息
        switch (labelType){
            case "ImageLabel":
                ImageLabelDataService imageLabelDataService = new ImageLabelDataImpl();
                if(!imageLabelDataService.deleteLabel(userId, taskId))
                    return new FailureException();
                break;
            case "FrameLabel":
                FrameLabelDataService frameLabelDataService = new FrameLabelDataImpl();
                if(frameLabelDataService.deleteLabel(userId, taskId))
                    return new FailureException();
                break;
            case "AreaLabel":
                AreaLabelDataService areaLabelDataService = new AreaLabelDataImpl();
                if(!areaLabelDataService.deleteLabel(userId, taskId))
                    return new FailureException();
                break;
            default:
                return new FailureException();
        }
        //修改任务状态
        if(workerDataService.reviseAcceptedTaskState(userId, taskId, AcceptedTaskState.ABANDONED_BY_WORKER))
            return new SuccessException();

        return new FailureException();
    }

    @Override
    public AcceptedTask getAcceptedTaskById(UserId userId, TaskId taskId) {
        return workerDataService.getAcceptedTaskById(userId, taskId);
    }


    @Override
    public int getUserRanking(UserId userId) {
            List<Worker> workerList = userDataService.getAllWorker();


            //自定义Comparator，为User类提供排序的比较方法
            Comparator comparator = new Comparator<User>() {
                @Override
                public int compare(User user1, User user2) {
                    //按照积分比较 进行排序
                    if((int)user1.getPrestige().value <= user2.getPrestige().value)
                        return 1;
                    else
                        return -1;
                }
            };

            //对所有用户根据积分值从高到低排序
            workerList.sort(comparator);

            User user = userDataService.getUserByUserId(userId);

            //若不存在该用户，则返回-1
            if(user == null)
                return -1;
            for(int i = 0; i < workerList.size(); i++){
                if(user.getUserId().value.equals(workerList.get(i).getUserId().value))
                    return i+1;
            }
            //若查找的用户不匹配，则返回-1
            return -1;
    }


    //获取工人所有已接受的任务
    private List<AcceptedTaskVO> getAcceptedTaskVOList(UserId userId){
        List<AcceptedTaskVO> list = new ArrayList<>();
        List<AcceptedTask> acceptedTaskList = workerDataService.getAcceptedTaskListByUserId(userId);
        PublishedTask publishedTask;
        Username username;
        LabelType labelType;
        TaskDescription taskDescription;
        for(AcceptedTask acceptedTask : acceptedTaskList){
                publishedTask = requestorDataService.getPublishedTask(acceptedTask.getTaskId());
                username = userDataService.getUserByUserId(userId).getUsername();
                labelType = publishedTask.getLabelType();
                taskDescription = publishedTask.getTaskDescription();
                list.add(new AcceptedTaskVO(acceptedTask, username, labelType, taskDescription));
        }
        return list;
    }

    //若工人曾经接受过某任务，则不允许他再次接受
    private boolean havePermissionToAcceptTask(UserId userId, TaskId taskId){
        //获取工人接受过的任务列表
        List<AcceptedTask> acceptedTaskList = workerDataService.getAcceptedTaskListByUserId(userId);
        for(AcceptedTask acceptedTask : acceptedTaskList){
            if(taskId.value.equals(acceptedTask.getTaskId().value)){
                return false;
            }
        }
        return true;
    }

}
