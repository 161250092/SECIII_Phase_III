package businessLogic.workerBL;

//import data.LabelData.LabelDataImpl;
//import data.LabelData.LabelDataService;
//import data.TaskData.TaskDataImpl;
//import data.TaskData.TaskDataService;
//import data.UserData.UserDataImpl;
//import data.UserData.UserDataService;
import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.task.AcceptedTask;
import model.user.Worker;
import model.vo.AcceptedTaskVO;
import model.vo.PublishedTaskVO;

import java.util.List;

public class WorkerBLImpl implements WorkerBLService {
    //private LabelDataService labelDataService;
    //private TaskDataService taskDataService;
    //private UserDataService userDataService;

    public WorkerBLImpl(){
        //labelDataService = new LabelDataImpl();
        //taskDataService = new TaskDataImpl();
        //userDataService = new UserDataImpl();
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(UserId userId) {
        return null;
    }

    @Override
    public List<PublishedTaskVO> getAvailableTaskList(UserId userId) {
        return null;
    }

    @Override
    public Exception acceptTask(UserId userId, List<TaskId> taskIdList) {
        return null;
    }

    @Override
    public Exception abandonTaskByWorker(UserId userId, TaskId taskId) {
        return null;
    }

    @Override
    public AcceptedTask getAcceptedTaskById(TaskId taskId) {
        return null;
    }

    @Override
    public int getUserRanking(UserId userId) {
        return 0;
    }

    @Override
    public Worker getWorkerInfo(UserId userId) {
        return null;
    }


    //@Override
    //public List<Task> getAcceptedAndAccomplishedTaskList(String userId) {
    //    return getTaskList(userId, true);
    //}
    //
    //@Override
    //public List<Task> getAcceptedButIncompleteTaskList(String userId) {
    //    return  getTaskList(userId, false);
    //}
    //
    //@Override
    //public List<Task> getAvailableTaskList(String userId) {
    //    List<Task> taskList = new ArrayList<>();
    //    List<String> assignedTaskIdList = null;
    //    List<String> acceptedAndAccomplishedTaskIdList = taskDataService.getAllAccomplishedAcceptedTaskID(userId);
    //    List<String> acceptedButIncompleteTaskIdList = taskDataService.getAllIncompleteAcceptedTaskID(userId);
    //    List<User> userList = userDataService.getAllUser();
    //
    //    //遍历所有用户
    //    for(User user : userList){
    //        //获取该用户发布且未完成的任务Id列表
    //        assignedTaskIdList = taskDataService.getAllIncompleteAssignedTaskID(user.getUserId());
    //        //遍历 任务Id列表，检查 需要接受任务的工人是否曾经接受或完成过某任务
    //        for(String taskId : assignedTaskIdList) {
    //            //如果 工人没有接受或完成过，则允许他接受任务
    //            if(acceptedAndAccomplishedTaskIdList.indexOf(taskId) == -1 && acceptedButIncompleteTaskIdList.indexOf(taskId) == -1)
    //                taskList.add(taskDataService.getTask(taskId));
    //        }
    //    }
    //    return taskList;
    //}
    //
    //@Override
    //public boolean acceptTask(String userId, String taskIdListJSON) {
    //    Gson gson = new GsonBuilder().create();
    //    List<String> taskIdList = gson.fromJson(taskIdListJSON, ArrayList.class);
    //
    //    for(String taskId : taskIdList){
    //        //获取任务详情
    //        Task task = taskDataService.getTask(taskId);
    //        //若任务为null 则报错
    //        if(task == null)
    //            return false;
    //
    //        //获取任务包含的图片数量
    //        int imageNumber = task.getImageFileName().length;
    //
    //        //如果后端调用失败 则报错
    //        if(!labelDataService.acceptTask(userId, taskId, imageNumber))
    //            return false;
    //    }
    //
    //    return true;
    //}
    //
    //@Override
    //public Task getTaskById(String taskId) {
    //    return taskDataService.getTask(taskId);
    //}
    //
    //@Override
    //public int getUserScore(String userId) {
    //    User user = userDataService.getUser(userId);
    //    return user.getScore();
    //}
    //
    //@Override
    //public int getUserRanking(String userId) {
    //    List<User> userList = userDataService.getAllUser();
    //
    //    //自定义Comparator，为User类提供排序的比较方法
    //    Comparator comparator = new Comparator<User>() {
    //        @Override
    //        public int compare(User user1, User user2) {
    //            //按照积分比较 进行排序
    //            if((int)user1.getScore() <= user2.getScore())
    //                return 1;
    //            else
    //                return -1;
    //        }
    //    };
    //
    //    //对所有用户根据积分值从高到低排序
    //    Collections.sort(userList, comparator);
    //
    //    User user = userDataService.getUser(userId);
    //
    //    //若不存在该用户，则返回-1
    //    if(user == null)
    //        return -1;
    //
    //    for(int i = 0; i < userList.size(); i++){
    //        if(user.getUserId().equals(userList.get(i).getUserId()))
    //            return i+1;
    //    }
    //
    //    //若查找的用户不匹配，则返回-1
    //    return -1;
    //}
    //
    //private List<Task> getTaskList(String userId, boolean isAccomplised){
    //    List<Task> taskList = new ArrayList<>();
    //    List<String> taskIdList = null;
    //    Task task = null;
    //
    //    if(isAccomplised)
    //        taskIdList = taskDataService.getAllAccomplishedAcceptedTaskID(userId);
    //    else
    //        taskIdList = taskDataService.getAllIncompleteAcceptedTaskID(userId);
    //
    //    if(taskIdList == null || taskIdList.size() == 0)
    //        return null;
    //    else{
    //        for(int i = 0; i < taskIdList.size(); i++){
    //            task = taskDataService.getTask(taskIdList.get(i));
    //            //假如无法通过taskId找到对应的Task，则返回null
    //            if(task == null)
    //                return null;
    //            taskList.add(task);
    //        }
    //        return taskList;
    //    }
    //}



}
