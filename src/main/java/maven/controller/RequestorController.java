package maven.controller;

import maven.businessLogic.requestorBL.RequestorBLImpl;
import maven.businessLogic.requestorBL.RequestorBLService;
import maven.businessLogic.requestorBL.RequestorBLStub;
import maven.model.JsonConverter;
import maven.model.primitiveType.*;
import maven.model.task.PublishedTask;
import maven.model.task.TaskType;
import maven.model.vo.AcceptedTaskVO;
import maven.model.vo.PublishedTaskVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 获取任务单张图片标注的价格数组
     * 与任务等级一一对应
     * @return 价格数组
     */
    @RequestMapping(value = "/requestor/getTaskUnitPriceList", method = RequestMethod.GET)
    public Map<TaskType, Cash> getTaskUnitPriceList(){
        return requestorBL.getTaskUnitPriceMap();
    }

    /**
     * 提交任务草稿
     * @param publishedTaskVOJSON 发布的任务VO的JSON字符串
     * @param imageFilenameListJSON 任务所含图片的文件名列表的JSON字符串
     * @return 上传任务信息的状态
     */
    @RequestMapping(value = "/requestor/submitTaskDraft", method = RequestMethod.GET)
    public Exception submitTaskDraft(String publishedTaskVOJSON, String imageFilenameListJSON){
        List<String> filenameStringList = (List<String>)JsonConverter.jsonToObject(imageFilenameListJSON, ArrayList.class);
        List<Filename> imageFilenameList = new ArrayList<>();
        for(String filenameString : filenameStringList)
            imageFilenameList.add(new Filename(filenameString));
        return requestorBL.uploadTaskInfo((PublishedTaskVO) JsonConverter.jsonToObject(publishedTaskVOJSON, PublishedTaskVO.class), imageFilenameList);
    }

    /**
     * 获取用户之前的任务草稿
     * @param userId 发布者Id
     * @return 任务草稿信息
     */
    @RequestMapping(value = "/requestor/getTaskDraftList", method = RequestMethod.GET)
    public List<PublishedTaskVO> getTaskDraftList(String userId){
        return requestorBL.getTaskDraftList(new UserId(userId));
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
     * 终止已发布的任务
     * @param taskId 任务Id
     * @return 后端处理任务撤销请求的结果
     */
    @RequestMapping(value = "/requestor/terminateTask", method = RequestMethod.GET)
    public Exception terminateTask(String taskId){
        return requestorBL.terminateTask(new TaskId(taskId));
    }

    /**
     * 修改已发布的任务（追加任务的悬赏金额）
     * @param taskId 任务Id
     * @param cash 修改后的金额数
     * @return 后端处理任务修改请求的结果
     */
    @RequestMapping(value = "/requestor/reviseTaskPrice", method = RequestMethod.GET)
    public Exception reviseTaskPrice(String taskId, double cash){
        return requestorBL.reviseTaskPrice(new TaskId(taskId), new Cash(cash));
    }

    /**
     * 修改已发布的任务（追加任务的需求人数）
     * @param taskId 任务Id
     * @param workerNum 修改后的需求工人数
     * @return 后端处理任务修改请求的结果
     */
    @RequestMapping(value = "/requestor/reviseTaskRequiredNum", method = RequestMethod.GET)
    public Exception reviseTaskRequiredNum(String taskId, int workerNum){
        return requestorBL.reviseTaskRequiredNum(new TaskId(taskId), new WorkerNum(workerNum));
    }

//    /**
//     * 获取发布者已发布的任务
//     * @param userId 发布者Id
//     * @return 已发布的任务列表
//     */
//    @RequestMapping(value = "/requestor/getPublishedTaskList", method = RequestMethod.GET)
//    public List<PublishedTaskVO> getPublishedTaskList(String userId){
//        return requestorBL.getPublishedTaskList(new UserId(userId));
//    }

//    /**
//     * 获取所有曾经参与过某任务的工人任务完成情况
//     * @param taskId 任务Id
//     * @param userId 发布者Id
//     * @return 参与过某任务的工人任务列表
//     */
//    @RequestMapping(value = "/requestor/getParticipantTaskList", method = RequestMethod.GET)
//    public List<AcceptedTaskVO> getParticipantTaskList(String taskId, String userId){
//        return null;
//    }

    /**
     * 获取工人已完成并待审核的任务列表
     * @param userId 发布者Id
     * @return 待审核的工人任务列表
     */
    @RequestMapping(value = "/requestor/getSubmittedTaskList", method = RequestMethod.GET)
    public List<AcceptedTaskVO> getSubmittedTaskList(String taskId, String userId){
        return requestorBL.getSubmittedTaskList(new TaskId(taskId), new UserId(userId));
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
     * @param userId 发布者Id
     * @return 发布且已完成任务的列表的Json字符串
     */
    @RequestMapping(value = "/requestor/getAssignedAndAccomplishedTaskList", method = RequestMethod.GET)
    public List<PublishedTaskVO> getAssignedAndAccomplishedTaskList(String userId){
        return requestorBL.getAssignedAndAccomplishedTaskList(new UserId(userId));
    }

    /**
     * 获取发布但未完成任务的列表
     * @param userId 发布者Id
     * @return 发布但未完成任务的列表的Json字符串
     */
    @RequestMapping(value = "/requestor/getAssignedButIncompleteTaskList", method = RequestMethod.GET)
    public List<PublishedTaskVO> getAssignedButIncompleteTaskList(String userId){
        return requestorBL.getAssignedButIncompleteTaskList(new UserId(userId));
    }

    /**
     * 获取具体的发布任务的信息
     * @param userId 发布者Id
     * @param taskId 任务Id
     * @return 任务详情
     */
    @RequestMapping(value="/requestor/getAssignedTask",method = RequestMethod.GET)
    public PublishedTask getAssignedTask(String userId, String taskId){
        return requestorBL.getAssignedTask(new UserId(userId), new TaskId(taskId));
    }

    /**
     * 查看已接受某任务的工人任务完成情况
     * @param userId 发布者Id
     * @param taskId 任务Id
     * @return 任务列表
     */
    @RequestMapping(value="/requestor/getAcceptedTaskVOList",method = RequestMethod.GET )
    public List<AcceptedTaskVO> getAcceptedTaskVOList(String userId, String taskId){
        return requestorBL.getAcceptedTaskVOList(new UserId(userId), new TaskId(taskId));
    }
}


