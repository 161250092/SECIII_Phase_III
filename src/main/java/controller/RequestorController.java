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
    public String getSubmittedTaskList(String userId){
        return JsonConverter.toJson(requestorBL.getSubmittedTaskList(new UserId(userId)));
    }

    /**
     * 审批工人的任务时 选择通过
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */

    public boolean passTask(String taskId, String userId){
        return requestorBL.passTask(new TaskId(taskId), new UserId(userId));
    }

    /**
     * 审批工人的任务时 选择驳回
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */

    public boolean rejectTask(String taskId, String userId){
        return requestorBL.rejectTask(new TaskId(taskId), new UserId(userId));
    }
    /**
     * 审批工人的任务时 选择废弃
     * @param taskId 任务Id
     * @param userId 工人Id
     * @return 后台处理的结果
     */

    public boolean abandonTask(String taskId, String userId){
        return requestorBL.abandonTask(new TaskId(taskId), new UserId(userId));
    }

    /**
     * 获取发布且已完成任务的列表
     * @param userId 用户Id
     * @return 发布且已完成任务的列表的Json字符串
     */
    public String getAssignedAndAccomplishedTaskList(String userId){
        return JsonConverter.toJson(requestorBL.getAssignedAndAccomplishedTaskList(new UserId(userId)));
    }

    /**
     * 获取发布但未完成任务的列表
     * @param userId 用户Id
     * @return 发布但未完成任务的列表的Json字符串
     */
    public String getAssignedButIncompleteTaskList(String userId){
        return JsonConverter.toJson(requestorBL.getAssignedButIncompleteTaskList(new UserId(userId)));
    }

    ///**
    // * 获取发布且已完成任务的列表
    // * @param userId 用户Id
    // * @return 发布且已完成任务的列表
    // */
    //@RequestMapping(value = "/RequestorController/getAssignedAndAccomplishedTaskList", method = RequestMethod.GET)
    //public List<Task> getAssignedAndAccomplishedTaskList(String userId){
    //    return requestorBLService.getAssignedAndAccomplishedTaskList(userId);
    //}
    //
    ///**
    // * 获取发布但未完成任务的列表
    // * @param userId 用户Id
    // * @return 发布但未完成任务的列表
    // */
    //@RequestMapping(value = "/RequestorController/getAssignedButIncompleteTaskList", method = RequestMethod.GET)
    //public List<Task> getAssignedButIncompleteTaskList(String userId){
    //    return requestorBLService.getAssignedButIncompleteTaskList(userId);
    //}

    ///**
    // * 获取发布任务总数
    // * @return 发布任务总数
    // */
    //@RequestMapping(value = "/RequestorController/getAssignedTaskNum", method = RequestMethod.GET)
    //public int getAssignedTaskNum(){
    //    return requestorBLService.getAssignedTaskNum();
    //}

    ///**
    // * 上传欲发布的任务信息
    // * @param taskJSON 任务的JSON字符串
    // * @return 上传任务信息的状态
    // */
    //public Exception uploadTaskInfo(String taskJSON){
    //    return requestorBL.uploadTaskInfo(taskJSON);
    //}
    //
    ///**
    // * 发布任务
    // * @param taskId 任务Id
    // * @return 后端处理任务发布请求的结果
    // */
    //@RequestMapping(value = "/RequestorController/assignTask", method = RequestMethod.GET)
    //public Exception assignTask(String taskId){
    //    return requestorBL.assignTask(new TaskId(taskId));
    //}
}
