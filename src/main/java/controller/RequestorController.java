package controller;

import businessLogic.requestorBL.RequestorBLImpl;
import businessLogic.requestorBL.RequestorBLService;
import model.JsonConverter;
import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 众包发起者控制器
 */
@RestController
public class RequestorController {

    private RequestorBLService requestorBL;

    public RequestorController(){
        requestorBL = new RequestorBLImpl();
    }

    /**
     * 获取工人已完成并待审核的任务列表
     * @param userId 用户Id
     * @return 待审核的任务列表的Json字符串
     */
    @RequestMapping(value = "/requestor/getSubmittedTaskList", method = RequestMethod.GET)
    public String getSubmittedTaskList(String userId){
        return JsonConverter.objectToJson(requestorBL.getSubmittedTaskList(new UserId(userId)));
    }

    /**
     * 审批工人的任务时 选择通过
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */
    @RequestMapping(value = "/requestor/passTask", method = RequestMethod.GET)
    public Exception passTask(String taskId, String userId){
        return requestorBL.passTask(new TaskId(taskId), new UserId(userId));
    }

    /**
     * 审批工人的任务时 选择驳回
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */
    @RequestMapping(value = "/requestor/rejectTask", method = RequestMethod.GET)
    public Exception rejectTask(String taskId, String userId){
        return requestorBL.rejectTask(new TaskId(taskId), new UserId(userId));
    }

    /**
     * 审批工人的任务时 选择废弃
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */
    @RequestMapping(value = "/requestor/abandonTaskByRequestor", method = RequestMethod.GET)
    public Exception abandonTaskByRequestor(String taskId, String userId){
        return requestorBL.abandonTaskByRequestor(new TaskId(taskId), new UserId(userId));
    }

    /**
     * 获取发布且已完成任务的列表
     * @param userId 用户Id
     * @return 发布且已完成任务的列表的Json字符串
     */
    @RequestMapping(value = "/requestor/getAssignedAndAccomplishedTaskList", method = RequestMethod.GET)
    public String getAssignedAndAccomplishedTaskList(String userId){
        return JsonConverter.objectToJson(requestorBL.getAssignedAndAccomplishedTaskList(new UserId(userId)));
    }

    /**
     * 获取发布但未完成任务的列表
     * @param userId 用户Id
     * @return 发布但未完成任务的列表的Json字符串
     */
    @RequestMapping(value = "/requestor/getAssignedButIncompleteTaskList", method = RequestMethod.GET)
    public String getAssignedButIncompleteTaskList(String userId){
        return JsonConverter.objectToJson(requestorBL.getAssignedButIncompleteTaskList(new UserId(userId)));
    }
}
