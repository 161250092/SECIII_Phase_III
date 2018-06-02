package maven.controller;

import maven.businessLogic.workerBL.WorkerBLImpl;
import maven.businessLogic.workerBL.WorkerBLService;
import maven.businessLogic.workerBL.WorkerBLStub;
import maven.model.JsonConverter;
import maven.model.primitiveType.TaskId;
import maven.model.primitiveType.UserId;
import maven.model.task.AcceptedTask;
import maven.model.user.Worker;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 众包工人控制器
 */
@RestController
public class WorkerController{

    private WorkerBLService workerBL;

    public WorkerController(){
        workerBL = new WorkerBLStub();
    }

    /**
     * 获取所有接受且已完成的任务
     * @param userId 工人Id
     * @return 所有已完成的任务
     */
    @RequestMapping(value = "/worker/getAcceptedAndAccomplishedTaskList", method = RequestMethod.GET)
    public List<AcceptedTaskVO> getAcceptedAndAccomplishedTaskList(String userId) {
        return workerBL.getAcceptedAndAccomplishedTaskList(new UserId(userId));
    }

    /**
     * 获取所有接受但未完成的任务
     * @param userId 工人Id
     * @return 所有未完成的任务
     */
    @RequestMapping(value = "/worker/getAcceptedButIncompleteTaskList", method = RequestMethod.GET)
    public List<AcceptedTaskVO> getAcceptedButIncompleteTaskList(String userId) {
        return workerBL.getAcceptedButIncompleteTaskList(new UserId(userId));
    }

    /**
     * 获取当前可以接受的任务
     * @return 所有当前可接受的任务
     */
    @RequestMapping(value = "/worker/getAvailableTaskList", method = RequestMethod.GET)
    public List<PublishedTaskVO> getAvailableTaskList(String userId) {
        return workerBL.getAvailableTaskList(new UserId(userId));
    }

    /**
     * 众包工人接受任务
     * @param userId 工人Id
     * @param taskIdListJSON 任务Id列表转化成的JSON字符串
     * @return 是否接受成功
     */
    @RequestMapping(value = "/worker/acceptTask", method = RequestMethod.GET)
    public Exception acceptTask(String userId, String taskIdListJSON) {
        List<String> taskId_String_List= (List<String>) JsonConverter.jsonToObject(taskIdListJSON, ArrayList.class);
        List<TaskId> taskIdList = new ArrayList<>();
        for(String taskId_String : taskId_String_List){
            System.out.println(taskId_String);
            taskIdList.add(new TaskId(taskId_String));
        }
        return workerBL.acceptTask(new UserId(userId), taskIdList);
    }

    /**
     * 众包工人放弃已接受的任务
     * @param taskId 工人Id
     * @return 是否放弃成功
     */
    @RequestMapping(value = "/worker/abandonTaskByWorker", method = RequestMethod.GET)
    public Exception abandonTaskByWorker(String userId, String taskId) {
        return workerBL.abandonTaskByWorker(new UserId(userId), new TaskId(taskId));
    }

    /**
     * 根据任务Id 获取工人已接受的任务
     * @param userId 工人Id
     * @param taskId 任务Id
     * @return 任务详情
     */
    @RequestMapping(value = "/worker/getAcceptedTaskById", method = RequestMethod.GET)
    public AcceptedTask getAcceptedTaskById(String userId, String taskId) {
        return workerBL.getAcceptedTaskById(new UserId(userId), new TaskId(taskId));
    }

    /**
     * 获取众包工人的排名
     * @param userId 工人Id
     * @return 工人排名
     */
    @RequestMapping(value = "/worker/getUserRanking", method = RequestMethod.GET)
    public int getUserRanking(String userId) {
        return workerBL.getUserRanking(new UserId(userId));
    }

}
