package controller;

import businessLogic.requestorBL.RequestorBLImpl;
import businessLogic.requestorBL.RequestorBLService;
import model.JsonConverter;
import model.primitiveType.Filename;
import model.primitiveType.TaskId;
import model.primitiveType.UserId;
import model.user.Requestor;
import model.vo.PublishedTaskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
     * 上传欲发布的任务信息
     * @param publishedTaskVOJSON 发布的任务VO的JSON字符串
     * @param imageFilenameListJSON 任务所含图片的文件名列表的JSON字符串
     * @return 上传任务信息的状态
     */
    @RequestMapping(value = "/requestor/uploadTaskInfo", method = RequestMethod.GET)
    public Exception uploadTaskInfo(String publishedTaskVOJSON, String imageFilenameListJSON){
        return requestorBL.uploadTaskInfo((PublishedTaskVO) JsonConverter.jsonToObject(publishedTaskVOJSON, PublishedTaskVO.class), (List<Filename>)JsonConverter.jsonToObject(imageFilenameListJSON, ArrayList.class));
    }

    /**
     * 发布任务
     * @param taskId 任务Id
     * @return 后端处理任务发布请求的结果
     */
    @RequestMapping(value = "/requestor/assignTask", method = RequestMethod.GET)
    public Exception assignTask(String taskId){
        return requestorBL.assignTask(new TaskId(taskId));
    }

    /**
     * 撤销已发布的任务
     * @param taskId 任务Id
     * @return 后端处理任务撤销请求的结果
     */
    @RequestMapping(value = "/requestor/revokeTask", method = RequestMethod.GET)
    public Exception revokeTask(String taskId){
        return requestorBL.revokeTask(new TaskId(taskId));
    }

    /**
     * 修改已发布的任务（追加任务的悬赏金额）
     * @param taskId 任务Id
     * @return 后端处理任务修改请求的结果
     */
    @RequestMapping(value = "/requestor/reviseTask", method = RequestMethod.GET)
    public Exception reviseTask(String taskId){
        return requestorBL.reviseTask(new TaskId(taskId));
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

    /**
     * 获取发布者的个人信息
     * @param userId 发布者Id
     * @return Requestor对象
     */
    @RequestMapping(value = "/requestor/getRequestorInfo", method = RequestMethod.GET)
    public Requestor getRequestorInfo(String userId){
        return requestorBL.getRequestorInfo(new UserId(userId));
    }
}
